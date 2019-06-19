$(document).ready(function() {

    var nowRefundId;

    getRefundStrategy();

    function getRefundStrategy() {
        getRequest(
            '/strategy/all',
            function (res) {
                var funds = res.content;
                renderRefund(funds);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );


    }

    function renderRefund(funds) {
        $(".content-refundStrategy").empty();
        getAllMovies();
        funds.forEach(function (fund) {
            var refundsDomStr = "";
            var movieDomStr = "";
            fund.movieVOList.forEach(function (movie) {

                movieDomStr += "<li class='activity-movie primary-text'>"+movie.name+"</li>";
                });


            refundsDomStr+=
                "<div class='refund-container'>" +
                "    <div class='refund-card'>" +
                "       <i class='icon-tags' style='font-size: 50px; margin-right: 7%'></i>"+
                "       <div class='refund-card-left'>" +
                "       <div class='refund-line'>" +
                "           <span class='title'>"+fund.name+"</span>" +
                "           <span class='gray-text'>"+fund.description+"</span>" +
                "       </div>" +
                "       <div class='refund-line'>" +
                "           <span>退票策略有效时间："+fund.limitedTime+"小时"+"</span>" +
                "       </div>" +
                "       <div class='refund-line'>" +
                "           <span>退票所需收取的手续费所占比例："+fund.bookingCharge+"%"+"</span>" +
                "       </div>" +
                "       <div class='refund-line'>" +
                "           <span>参与电影：</span>" +
                "               <ul>"+movieDomStr+"</ul>" +
                "       </div>" +
                "       </div>"+
                "       <div class='refund-card-right refund-card-right-"+fund.id+"'>" +
                "       </div>"+
                "    </div>" +
                "</div>";

            $(".content-refundStrategy").append(refundsDomStr);
            var btn0 = document.createElement("div");
            btn0.className='refund-right-btn';
            btn0.innerHTML = "<a type='button' class='strategy-edit btn btn-default' data-backdrop='static' data-toggle='modal' data-target='#refundEditModal'><span>修改</span></a>";
            btn0.onclick = function () {
                nowRefundId=fund.id;
                $("#refund-edit-name-input").val(fund.name);
                $("#refund-edit-description-input").val(fund.description);
                $("#refund-edit-valid-time-input").val(fund.limitedTime);
                $("#refund-edit-charge-input").val(fund.bookingCharge);

            };
            $(".refund-card-right-"+fund.id).append(btn0);
            var btn1 = document.createElement("div");
            btn1.className='refund-right-btn';
            btn1.innerHTML = "<a type='button' class='strategy-delete btn btn-default' ><span>删除</span></a>";
            btn1.onclick = function () {
                var r = confirm("确认要删除该退票策略吗");
                if (r) {
                    deleteRequest(
                        '/strategy/delete?id=' + fund.id,
                        null,
                        function (res) {
                            if (res.success) {
                                getRefundStrategy();
                            } else {
                                alert(res.message);
                            }
                        },
                        function (error) {
                            alert(JSON.stringify(error));
                        }
                    );
                }
            };
            $(".refund-card-right-"+fund.id).append(btn1);
        });

    }

    function getAllMovies() {
        document.getElementById("refund-movie-input").innerHTML="";
        document.getElementById("selected-movies").innerHTML="";
        getRequest(
            '/movie/refund',
            function (res) {
                var movieFormList = res.content;
                $('#refund-movie-input').append("<option value="+ -1 +">所有电影</option>");
                movieFormList.forEach(function (movie) {
                    $('#refund-movie-input').append("<option value="+ movie.id +">"+movie.name+"</option>");
                });
            },
            function (error) {
                alert(error);
            }
        );
    }

    $("#canceDeleteAdd").click(function () {
        selectedMovieIds.clear();
        selectedMovieNames.clear();
        getRefundStrategy();
    })

    $("#strategy-form-btn").click(function () {
        var form = {
            name: $("#refund-name-input").val(),
            description: $("#refund-description-input").val(),
            limitedTime: $("#refund-valid-time-input").val(),
            bookingCharge: $("#refund-charge-input").val(),
            movieFormList: [...selectedMovieIds]
    };

        postRequest(
            '/strategy/add',
            form,
            function (res) {
                if(res.success){
                    getRefundStrategy();
                    selectedMovieIds.clear();
                    selectedMovieNames.clear();
                    $("#refundModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {

                alert(JSON.stringify(error));
            }
        );
    });

    //ES6新api 不重复集合 Set
    var selectedMovieIds = new Set();
    var selectedMovieNames = new Set();

    $('#refund-movie-input').change(function () {
        var movieId = $('#refund-movie-input').val();
        var movieName = $('#refund-movie-input').children('option:selected').text();
        if(movieId==-1){
            selectedMovieIds.clear();
            selectedMovieNames.clear();
        } else {
            selectedMovieIds.add(movieId);
            selectedMovieNames.add(movieName);
        }
        renderSelectedMovies();
    });

    //渲染选择的参加活动的电影
    function renderSelectedMovies() {
        $('#selected-movies').empty();
        var moviesDomStr = "";
        selectedMovieNames.forEach(function (movieName) {
            moviesDomStr += "<span class='label label-primary'>"+movieName+"</span>";
        });
        $('#selected-movies').append(moviesDomStr);
    }


    $("#strategy-edit-form-btn").click(function () {
        var formData = getEditRefundForm();
        postRequest(
            '/strategy/update',
            formData,
            function (res) {
                getRefundStrategy();
                selectedMovieIds.clear();
                selectedMovieNames.clear();
                $("#refundEditModal").modal('hide');
            },
            function (error) {
                alert(error);
            });
    });
    function getEditRefundForm() {
        return {
            id: nowRefundId,
            name: $("#refund-edit-name-input").val(),
            description: $("#refund-edit-description-input").val(),
            limitedTime: $("#refund-edit-valid-time-input").val(),
            bookingCharge: $("#refund-edit-charge-input").val(),
            movieFormList: [...selectedMovieIds]
        };
    }

});
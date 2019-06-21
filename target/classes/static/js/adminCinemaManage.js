$(document).ready(function() {

    /*
    初始化界面
    */
    var canSeeDate = 0;
    var nowHallId;
    getCanSeeDayNum();
    getCinemaHalls();

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    /*
   获取影厅座位布局
   */
    function renderHall(halls) {
        $("#hall-card").empty();
        halls.forEach(function (hall) {
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = "";
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var hallDom =
                "<div class='cinema-hall'>" +
                "<div class='cinema-hall-header'>" +
                "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "<span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row +"</span>" +
                "</div>" +
                "<div class='cinema-seat'>" + seat +
                "</div>" +
                "</div>"+
                "<div style='display:flex;justify-content:center;margin-bottom:35px;' id='cinema-hall-btn-"+hall.id+"'>" +
                "</div>"
            ;
            $('#hall-card').append(hallDom);

            /*
           为每一个影厅添加修改影厅信息按钮
           */
            var bt0=document.createElement("div");
            bt0.className='cinema-hall-btn';
            bt0.innerHTML= "<a type='button' class='btn btn-primary' data-backdrop='static' data-toggle='modal' data-target='#hallEditModal'>修改影厅信息</a>";
            bt0.onclick=function() {
                nowHallId=hall.id;
                $("#edit-hall-name-input" ).val(hall.name);
                $("#edit-hall-row-input" ).val(hall.row);
                $("#edit-hall-column-input" ).val(hall.column);
            };
            $('#cinema-hall-btn-'+hall.id).append(bt0);

            /*
           为每一个影厅添加删除影厅信息按钮
           */
            var bt1=document.createElement("div");
            bt1.className='cinema-hall-btn';
            bt1.innerHTML= "<a type='button' class='btn btn-danger' id='delete-btn-"+ hall.id +" '><span>删除影厅信息</span></a>";
            bt1.onclick=function() {
                getRequest(
                    '/hall/get?id='+hall.id,
                    function (res) {
                        if(res.content.isChangable){
                            var r=confirm("确认要删除该影厅信息吗");
                            if(r){
                                deleteRequest(
                                    '/hall/delete?id='+hall.id,
                                    null,
                                    function (res) {
                                        if (res.success){
                                            getCinemaHalls();
                                        }else {
                                            alert(res.message);
                                        }
                                    },
                                    function (error) {
                                        alert(JSON.stringify(error));
                                    }
                                );
                            }
                        }
                        else{
                            alert("该影厅已经排片，无法删除影厅信息");
                            getCinemaHalls();
                            $("#hallEditModal").modal('hide');
                        }
                    },
                    function (error) {
                        alert(error);
                    }
                );
            };
            $('#cinema-hall-btn-'+hall.id).append(bt1);
        });
    }

    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    $('#canview-modify-btn').click(function () {
        $("#canview-modify-btn").hide();
        $("#canview-set-input").val(canSeeDate);
        $("#canview-set-input").show();
        $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day: dayNum},
            function (res) {
                if (res.success) {
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

    $("#hall-form-btn").click(function () {
        var formData = getNewHallForm();
        if (!validateNewHallForm(formData)) {
            return;
        }
        postRequest(
            '/hall/add',
            formData,
            function (res) {
                getCinemaHalls();
                $("#hallModal").modal('hide');
            },
            function (error) {
                alert(error);
            });
    });

    function getNewHallForm() {
        return {
            name: $('#new-hall-name-input').val(),
            row: $('#new-hall-row-input').val(),
            column: $('#new-hall-column-input').val(),
        };
    }

    function validateNewHallForm(data) {
        var isValidate = true;
        if (!data.name) {
            isValidate = false;
            $('#new-hall-name-input').parent('.form-group').addClass('has-error');
        }
        if ((!data.row) || (!(data.row % 1 === 0))) {
            isValidate = false;
            $('#new-hall-row-input').parent('.form-group').addClass('has-error');
        }
        if ((!data.column) || (!(data.column % 1 === 0))) {
            isValidate = false;
            $('#new-hall-column-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }

    /*
    修改影厅信息后，刷新界面
    */
    $("#hall-edit-form-btn").click(function () {
        getRequest(
            '/hall/get?id='+nowHallId,
            function (res) {
               if(res.content.isChangable){
                   var formData = getEditHallForm();
                   if (!validateEditHallForm(formData)) {
                       return;
                   }
                   postRequest(
                       '/hall/update',
                       formData,
                       function (res) {
                           getCinemaHalls();
                           $("#hallEditModal").modal('hide');
                       },
                       function (error) {
                           alert(error);
                       });
               }
               else{
                   alert("该影厅已经排片，无法修改影厅信息");
                   getCinemaHalls();
                   $("#hallEditModal").modal('hide');
               }
            },
            function (error) {
                alert(error);
            }
        );
    });

    function getEditHallForm() {
        return {
            id: nowHallId,
            name: $('#edit-hall-name-input').val(),
            row: $('#edit-hall-row-input').val(),
            column: $('#edit-hall-column-input').val(),
        };
    }


    function validateEditHallForm(data) {
        var isValidate = true;
        if (!data.name) {
            isValidate = false;
            $('#edit-hall-name-input').parent('.form-group').addClass('has-error');
        }
        if ((!data.row) || (!(data.row % 1 === 0))) {
            isValidate = false;
            $('#edit-hall-row-input').parent('.form-group').addClass('has-error');
        }
        if ((!data.column) || (!(data.column % 1 === 0))) {
            isValidate = false;
            $('#edit-hall-column-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }
});




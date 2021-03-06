$(document).ready(function() {


    getAllStrategies();

    function getAllStrategies() {
        getRequest(
            '/vipStrategy/get',
            function (res) {
                var strategies = res.content;
                if (strategies!=null) {
                    renderStrategies(strategies);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderStrategies(strategies) {
        $('.content-vipStrategy').empty();
        var strategiesDomStr = "";

       //将每一项策略主要内容展示出来
        strategies.forEach(function (strategy) {
            strategiesDomStr =
                "<div class='strategy-container' >" +
                "    <div class='strategy-card card'>" +
                "       <div class='stratrgy-line'>" +
                "           <span class='title'>" + strategy.name + "</span>" +
                "       </div>" +
                "       <div class='stratrgy-line'>" +
                "           <span>目标金额：" + strategy.targetMoney + "</span>" +
                "       </div>" +
                "       <div class='stratrgy-line'>" +
                "           <span>赠送金额：" + strategy.giftMoney + "</span>" +
                "       </div>" +
                "    </div>" +
                "    <div class='strategy-price'>" +
                "        <h1 class='price-show'>￥"+strategy.price+"</h1>" +
                "        <div class='strategy-operation' id='strategy-operations"+strategy.id+"'>" +
                "        </div>" +
                "    </div>" +
                "</div>";

            $('.content-vipStrategy').append(strategiesDomStr);
            if (strategy.state==0) {
                //为每一项策略添加修改按钮
                var btn0 = document.createElement("div");
                btn0.className = 'vip-card-btn';
                btn0.innerHTML = "<a type='button' class='strategy-edit btn btn-default' data-backdrop='static' data-toggle='modal' data-target='#strategyEditModal'><span>修改</span></a>";
                btn0.onclick = function () {
                    $("#strategy-edit-name-input").val(strategy.name);
                    $("#strategy-edit-price-input").val(strategy.price);
                    $("#target-edit-money-input").val(strategy.targetMoney);
                    $("#gift-edit-money-input").val(strategy.giftMoney);
                    $('#strategyEditModal')[0].dataset.strategyId = strategy.id;
                };
                $('#strategy-operations' + strategy.id).append(btn0);

                //为每一项策略添加删除按钮
                var btn1 = document.createElement("div");
                btn1.className = 'vip-card-btn';
                btn1.innerHTML = "<a type='button' class='strategy-delete btn btn-default' ><span>下架</span></a>";
                btn1.onclick = function () {
                    var r = confirm("确认要下架该会员卡吗");
                    if (r) {
                        postRequest(
                            '/vipStrategy/takeoff',
                            strategy,
                            function (res) {
                                if (res.success) {
                                    getAllStrategies();
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
                $('#strategy-operations' + strategy.id).append(btn1);
            } else {
                $('#strategy-operations' + strategy.id).append("<h3>已下架</h3>");
            }
        });
    }
    //发布会员卡
    $('#strategy-form-btn').click(function () {
        var form={
            name:$('#strategy-name-input').val(),
            price:$('#strategy-price-input').val(),
            targetMoney:$('#target-money-input').val(),
            giftMoney:$('#gift-money-input').val()
        };

        postRequest(
            '/vipStrategy/publish',
            form,
            function (res) {
                if (res.success) {
                    getAllStrategies();
                    $('#strategyModal').modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    //修改会员卡
    /*$(document).on('click','.strategy-edit',function (e) {
        var strategy = JSON.parse(e.target.dataset.updatestrategy);
        $("#strategy-edit-name-input").val(strategy.name);
        $("#strategy-edit-price-input").val(strategy.price);
        $("#target-edit-money-input").val(strategy.targetMoney);
        $("#gift-edit-money-input").val(strategy.giftMoney);
        $('#strategyEditModal').modal('show');
        $('#strategyEditModal')[0].dataset.strategyId = strategy.id;

    });*/

    $('#strategy-edit-form-btn').click(function () {
        var form={
            id:Number($('#strategyEditModal')[0].dataset.strategyId),
            name:$('#strategy-edit-name-input').val(),
            price:$('#strategy-edit-price-input').val(),
            targetMoney:$('#target-edit-money-input').val(),
            giftMoney:$('#gift-edit-money-input').val()
        };
        postRequest(
            '/vipStrategy/update',
            form,
            function (res) {
                if (res.success) {
                    getAllStrategies();
                    $('#strategyEditModal').modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    //删除会员卡
    /*$(document).on('click','.strategy-delete',function (e) {
        var id=JSON.parse(e.target.dataset.deletestrategy);
        var r=confirm("确认要删除该会员策略吗");
        if (r){
            deleteRequest(
                '/vipStrategy/delete?id='+id,
                null,
                function (res) {
                    if (res.success) {
                        getAllStrategies();
                    } else {
                        alert(res.message);
                    }
                },
                function (error) {
                    alert(JSON.stringify(error));
                }
            );
        }
    })*/

});


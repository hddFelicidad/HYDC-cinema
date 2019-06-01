var orderId;
var cont;
var useVIP=true;
var isVIP;

$(document).ready(function () {
    orderId=parseInt(window.location.href.split('?')[1].split('=')[1]);

    getOrderDetail();
});

function getOrderDetail() {
    getRequest(
        '/order/detail?orderId='+orderId,
        function (res) {
            if (res.success) {
                renderOrderDetail(res.content);
            } else {
                alert(res.content)
            }
        },
        function (error) {
            alert(error);
        }
    );
}

function renderOrderDetail(order) {
    if (order.state=="已完成且可退票"){
        $(".finished").css("display","");
        $(".refund-succeed").css("display","none");
        $(".unpaid").css("display","none");
        $("#btn-repay").css("display","none");
        $("#btn-refund").css("display","");
    }else if (order.state=="已完成但不可退票"){
        $(".finished").css("display","");
        $(".refund-succeed").css("display","none");
        $(".unpaid").css("display","none");
        $("#btn-repay").css("display","none");
        $("#btn-refund").css("display","none");
    } else if (order.state=="退款成功") {
        $(".finished").css("display","none");
        $(".refund-succeed").css("display","");
        $(".unpaid").css("display","none");
        $("#btn-repay").css("display","none");
        $("#btn-refund").css("display","none");
    } else if (order.state=="待支付") {
        $(".unpaid").css("display","");
        $("#btn-repay").css("display","");
        $(".refund-succeed").css("display","none");
        $(".finished").css("display","none");
        $("#btn-refund").css("display","none");
    }

    $(".order-id").text("订单号："+order.id);
    $(".movie-name").text(order.movieName);
    $(".showtime").text(order.scheduleTime.substring(5, 7) + "月" + order.scheduleTime.substring(8, 10) + "日 " + order.scheduleTime.substring(11, 16));
    $(".hall-name").text(order.seats.split(" ")[0]);
    $(".price").text(order.realMoney);
    $("#repay-amount").html("<b>金额：</b>" + order.realMoney + "元");

    var seatDomStr="";
    $(".seats").empty();
    order.tickets.forEach(function (ticket) {
        seatDomStr+=
            "<span>"+
            "<i id='column-num'>"+(ticket.rowIndex+1)+"</i>"+
            "排"+
            "<i id='row-num'>"+(ticket.columnIndex+1)+"</i>"+
            "座"+
            "</span>"
    });
    $(".seats").append(seatDomStr);
}

function refund() {
    getRequest(
        '/order/getStrategy?orderId='+orderId,
        function (res) {
            if (res.success) {
                 cont=res.content;
                 var contDomStr="";
              $("#refundInformation").empty();
              contDomStr =
                "<div id='modal-body-refund' style='margin-bottom: 20px;'>"+
                "<div id='member-balance-originalPrice'><b>订单原金额：</b>&nbsp;&nbsp;"+cont.originalPrice+"元"+"</div>"+
                "<div id='member-balance-originalPrice'><b>退款手续费：</b>&nbsp;&nbsp;"+cont.servicePrice+"元"+"</div>"+
                "<div id='member-balance-originalPrice'><b>可退还金额：</b>&nbsp;&nbsp;"+cont.refundPrice+"元"+"</div>"+
                "</div> ";
              $("#refundInformation").append(contDomStr);

            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        });

}
function refundConfirmClick() {
    postRequest(
        '/order/cancel?orderId='+orderId,
              cont,
        function (res) {
            if (res.success) {
                alert(res.content);
                $('#refundModal').modal('hide');
                getOrderDetail();
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        });
}
function refundCancelClick() {
    cont='';
    getOrderDetail();
}
function repay() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            isVIP = res.success;
            useVIP=res.success;
            if (isVIP) {
                $('#member-balance-repay').html("<div><b>会员卡余额：</b>" + res.content.balance.toFixed(2) + "元</div>");
            } else {
                $("#member-repay").css("display", "none");
                $("#nonmember-repay").addClass("active");

                $("#modal-body-member-repay").css("display", "none");
                $("#modal-body-nonmember-repay").css("display", "");
            }
        },
        function (error) {
            alert(error);
        });
}

function switchRepay(type) {
    useVIP = (type == 0);
    if (type == 0) {
        $("#member-repay").addClass("active");
        $("#nonmember-repay").removeClass("active");

        $("#modal-body-member-repay").css("display", "");
        $("#modal-body-nonmember-repay").css("display", "none");
    } else {
        $("#member-repay").removeClass("active");
        $("#nonmember-repay").addClass("active");

        $("#modal-body-member-repay").css("display", "none");
        $("#modal-body-nonmember-repay").css("display", "");
    }
}

function repayConfirmClick() {
    if (useVIP) {
        repayByVIP();
    } else {
        if (validateForm()) {
            if ($('#user-repay-cardNum').val() === "123123123" && $('#user-repay-cardPwd').val() === "123123") {
                repayByCard();
            } else {
                alert("银行卡号或密码错误");
            }
        }
    }
}

function repayByVIP() {
    postRequest(
        "/ticket/vip/repay?orderId="+orderId,
        null,
        function (res) {
            if(res.success) {
                $('#repayModal').modal('hide');
                getOrderDetail();
            }
            else{
                alert ("余额不足")
            }

        },
        function (error) {
            alert(error);
        });

}

function repayByCard() {
    postRequest(
        "/ticket/repay?orderId="+orderId,
        null,
        function (res) {
            $('#repayModal').modal('hide');
            getOrderDetail();
        },
        function (error) {
            alert(error);
        });

}

function validateForm() {
    var isValidate = true;
    if (!$('#user-repay-cardNum').val()) {
        isValidate = false;
        $('#user-repay-cardNum').parent('.form-group').addClass('has-error');
        $('#user-repay-cardNum-error').css("visibility", "visible");
    }
    if (!$('#user-repay-cardPwd').val()) {
        isValidate = false;
        $('#user-repay-cardPwd').parent('.form-group').addClass('has-error');
        $('#user-repay-cardPwd-error').css("visibility", "visible");
    }
    return isValidate;
}
$(document).ready(function () {
   getRecharge();

    function getRecharge() {
        getRequest(
            "/order/getRecharge?userId=" + sessionStorage.getItem('id'),
            function (res) {
                if (res.success) {
                    renderRechargeRecord(res.content);
                } else {
                    alert(res.content);
                }
            },
            function (error) {
                alert(error);
            });
    }

    function renderRechargeRecord(list) {
        $('.table tbody').empty();
        var rechargeDomStr="";
        list.forEach(function (record) {
            rechargeDomStr+="<tr>" +
                "<td>"+record.tradeType+"</td>"+
                "<td>"+record.tradeTime.toString().substring(0, 19).replace('T',' ')+"</td>"+
                "<td>"+record.rechargeMoney+"</td>"+
                "<td>"+record.giftMoney+"</td>"+
                "<td>"+record.balance+"</td>"+
                "</tr>"
        });
        $('.table tbody').append(rechargeDomStr);
    }
});
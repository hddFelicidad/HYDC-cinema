$(document).ready(function () {
    getConsumptionRecords();
    
    function getConsumptionRecords() {
        getRequest(
            '/order/getConsumption?userId=' + sessionStorage.getItem('id'),
            function (res) {
                if (res.success) {
                    var orderList = res.content;
                    var orderDomStr = "";
                    orderList.forEach(function (order) {
                        orderDomStr +=
                            "<div class='order-box' data-orderid='" + order.id + "'>" +
                            "    <div class='order-header'>" +
                            "        <span class='order-date'>" + order.time.substring(0,10) + "</span>" +
                            "        <span class='order-id'>订单号：" + order.id + "</span>" +
                            "    </div>" +
                            "    <div class='order-body'>" +
                            "        <div class='poster'>" +
                            "            <img src='" + order.posterUrl + "'>" +
                            "        </div>" +
                            "        <div class='order-content'>" +
                            "            <div class='movie-name'>" + order.movieName + "</div>" +
                            "            <div class='cinema-name'>HYDC影城</div>" +
                            "            <div class='hall-ticket'>" +
                            "                   <span>" + order.seats.substring(order.seats.indexOf(' ')+1) + "</span>" +
                            "            </div>" +
                            "            <div class='show-time'>" + order.scheduleTime.substring(5, 7) + "月" + order.scheduleTime.substring(8, 10) + "日 " + order.scheduleTime.substring(11, 16) + "</div>" +
                            "        </div>" +
                            "        <div class='order-price'>￥" + order.realMoney + "</div>" +
                            "        <div class='order-status'>" + order.state + "</div>" +
                            "        <div class='actions'>" +
                            "            <div>" +
                            "                <a href='/user/orderDetail?id=" + order.id + "' class='order-detail'>查看详情</a>" +
                            "            </div>" +
                            "        </div>" +
                            "    </div>" +
                            "</div>"
                    });
                    $('.consumption-records').append(orderDomStr);
                } else {
                    alert(res.content);
                }
            },
            function (error) {
                alert(error);
            });
    }
});
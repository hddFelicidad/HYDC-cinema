$(document).ready(function () {
    getAiVIPList();
    function getAiVIPList() {
        getRequest(
            '/present/rank/AI' ,
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
        /*var list=[
        VIPRecordV0={
            userId:1,
            vipName:"王者会员卡",
            UserName:"zc" ,
            totalAmount:100,
            joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
            lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
    }, VIPRecordV0={
                userId:2,
                vipName:"zuanshi会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },
            VIPRecordV0={
                userId:3,
                vipName:"哈哈哈会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },

        ];
        renderTicketList(list);*/
    }
    function getAmountVIPList() {
        getRequest(
            '/present/rank/amount' ,
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
        /*
        var list=[
            VIPRecordV0={
                vipName:"会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            }, VIPRecordV0={
                vipName:"wangzha会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },
            VIPRecordV0={
                vipName:"lalala会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },

        ];
        renderTicketList(list);*/
    }

    function getDateVIPList() {
        getRequest(
            '/present/rank/date' ,
            function (res) {
                renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
        /*
        var list=[
            VIPRecordV0={
                UserId:1,
                vipName:"haaha会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            }, VIPRecordV0={
                UserId:2,
                vipName:"da员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"2ksakosaphjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },
            VIPRecordV0={
                UserId:3,
                vipName:"oapopsapoa会员卡",
                UserName:"zc" ,
                totalAmount:100,
                joinDate :"23-1jdcfpiuheqpfhjvcjnjfjkjfdjlkalk",
                lastOrderDate:"cpoeoijejdepoide3hjvdeoiuewqojoq",
            },

        ];
        renderTicketList(list);
        */
    }

    // TODO:填空
    function renderTicketList(list) {

        $('.table tbody').empty();
        var listDomStr = '';
        list.forEach(function (VIPRecordVO) {

            listDomStr += "<tr>" +
                "<td>" + VIPRecordVO.vipName + "</td>" +
                "<td>" +  VIPRecordVO.userName  + "</td>" +
                "<td>" + VIPRecordVO.totalAmount + "元" + "</td>" +
                "<td>" + VIPRecordVO.joinDate.toString().substring(0, 19).replace('T',' ')+ "</td>" +
                "<td>" + VIPRecordVO.lastOrderDate.toLocaleString().substring(0, 19).replace('T',' ')
             +"<input type='checkbox' class='vip-select'name='category' style='display:none; zoom: 60%'  value="+ VIPRecordVO.userId +">"+"</td></tr>";

        });
        $('.table tbody').append(listDomStr);
        /*
        $('.table tbody').empty();
        var listDomStr = '';
        list.forEach(function (VIPRecordVO) {
            listDomStr += "<tr>" +
                "<td>" + VIPRecordVO.vipName + "</td>" +
                "<td>" +  VIPRecordVO.UserName  + "</td>" +
                "<td>" + VIPRecordVO.totalAmount + "元" + "</td>" +
                "<td>" + VIPRecordVO.joinDate+ "</td>" +
                "<td>" + VIPRecordVO.lastOrderDate+ "</td>";
            listDomStr +="<td><input type='checkbox' class='vip-select'name='category' style='display:none'  value="+ VIPRecordVO.userId +">"+"</td></tr>";
        });
        $('.table tbody').append(listDomStr);
    */}

    function getCouponList(){
        getRequest(
           '/present/getCoupon' ,
           function (res) {
               renderCouponList(res.content);
           },
           function (error) {
               alert(error);
           });
        /*
        var list=[
            {
                couponId:1,
                couponName:"sadww",
            },
            {
                couponId:2,
                couponName:"djaoihi",
            }
        ]
        renderCouponList(list);
    */}
    function renderCouponList(list){
        $('#selected-coupon-input').empty();
        $('#selected-coupon-input').append("<option style='display:none' value='-1' ></option>");
        list.forEach(function (CouponVO) {
             $('#selected-coupon-input').append("<option value="+ CouponVO.id +">"+CouponVO.name+"</option>");
        });


    }

    $('#ai-sort').click(function () {
        getAiVIPList();
        $('#sort-btn').html('智能排序 <span class="caret"></span>');
    });
    $('#amount-sort').click(function () {
        getAmountVIPList();
        $('#sort-btn').html('金额排序 <span class="caret"></span>');
    });
    $('#date-sort').click(function () {
        getDateVIPList();
        $('#sort-btn').html('日期排序 <span class="caret"></span>');
    });
    $('#selectVIP').click(function () {
        $(".vip-select").css("display","");
        $("#selectStrategy").css("display","");
        $("#cancelSelectStartegy").css("display","");
    });
    $('#cancelSelectStartegy').click(function () {
        $(".vip-select").css("display","none");
        $("#selectStrategy").css("display","none");
        $("#cancelSelectStartegy").css("display","none");
    });

    $('#selectStrategy').click(function () {
        getCouponList();
    });
    $('#strategy-form-btn').click(function () {
        var formData=getVIPForm();
        var couponId=parseInt($('#selected-coupon-input').val());
        if(couponId==-1){
            alert("请选择优惠券");
            return;
        }
        postRequest(
            '/present/complete?couponId='+couponId,
            formData,
            function (res) {
                if (res.success) {
                    alert("赠送成功");
                    $(".vip-select").css("display","none");
                    $("#selectStrategy").css("display","none");
                    $("#cancelSelectStartegy").css("display","none");
                    $("#selectCouponModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });
    function getVIPForm() {
        var array=[];
        var items=document.getElementsByName('category');
        for (i=0;i<items.length;i++){
            if(items[i].checked){
                array.push(parseInt(items[i].value));
            }
        }
        return array;
    }
});
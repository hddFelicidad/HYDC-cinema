var isBuyState = true;
var vipCardId;
var isVIP;
var cardInfo;
$(document).ready(function () {
    getVIP();
    getCoupon();
});
function getVIP() {
    getRequest(
        '/vip/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                // 是会员
                isVIP = true;
                $("#member-card").css("visibility", "visible");
                $("#member-card").css("display", "");
                $("#nonmember-card").css("display", "none");
                $("#buy-member-card").css("display","none");


                vipCardId = res.content.id;
                $("#member-id").text(res.content.id);
                $("#member-description").text("满" + res.content.targetMoney + "送" + res.content.giftMoney);
                $("#member-balance").text("¥" + res.content.balance.toFixed(2));
                $("#member-joinDate").text(res.content.joinDate.substring(0, 10));
            } else {
                // 非会员
                isVIP = false;
                getOneCard();
                $("#member-card").css("display", "none");
                $("#nonmember-card").css("display", "");
                $("#buy-member-card").css("display","none");
            }
        },
        function (error) {
            alert(error);
        });
}
function getOneCard() {
    getRequest(
        '/vip/getValueCard',
        function (res) {
            if (res.success) {
                var card=res.content;
                var cardDomStr="";
                $("#card-recommend").empty();
                cardDomStr =
                    "<div class='price'><b>" + card.price + "</b>元/张</div>" +
                    "<div class='description'>充值优惠：满" + card.targetMoney + "送" + card.giftMoney + "。永久有效。</div>" +
                    "<button onclick='buyClick(this)' data-info='"+JSON.stringify(card)+"'>立即购买</button>";
                $("#card-recommend").append(cardDomStr);

            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        });
}

function showMoreCards() {
    $('#member-card').css("display", "none");
    $("#nonmember-card").css("display", "none");
    $("#all-coupon").css("display", "none");
    $("#buy-member-card").css("display", "");
    getRequest(
        '/vip/getVIPInfo',
        function (res) {
            if (res.success) {
                var cards = res.content;
                renderCards(cards);
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        });
}

function renderCards(cards) {
    $('.card-list').empty();
    var cardDomStr = "";
    cards.forEach(function (card) {
        cardDomStr +=
            "<div class='info'>" +
            "    <div class='price'><b>" + card.price + "</b>元/张</div>" +
            "    <div class='description'>充值优惠：满" + card.targetMoney + "送" + card.giftMoney + "。永久有效。</div>" +
            "    <button onclick='buyClick(this)' data-info='"+JSON.stringify(card)+"'>立即购买</button>" +
            "</div>";

    });
    $('#cards-list').html(cardDomStr);
}

function back() {
    $("#buy-member-card").css("display", "none");
    if (isVIP) {
        $("#member-card").css("visibility", "visible");
        $("#member-card").css("display", "");
    } else {
        $("#nonmember-card").css("display", "");
    }
    $("#all-coupon").css("display", "");
}

function buyClick(obj) {
    cardInfo=JSON.parse($(obj).attr("data-info"));
    clearForm();
    $('#buyModal').modal('show')
    $("#userMember-amount-group").css("display", "none");
    isBuyState = true;
}

function chargeClick() {
    clearForm();
    $('#buyModal').modal('show')
    $("#userMember-amount-group").css("display", "");
    isBuyState = false;
}

function clearForm() {
    $('#userMember-form input').val("");
    $('#userMember-form .form-group').removeClass("has-error");
    $('#userMember-form p').css("visibility", "hidden");
}

function confirmCommit() {
    if (validateForm()) {
        if ($('#userMember-cardNum').val() === "123123123" && $('#userMember-cardPwd').val() === "123123") {
            if (isBuyState) {
                postRequest(
                    '/vip/add?userId=' + sessionStorage.getItem('id'),
                    cardInfo,
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("购买会员卡成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            } else {
                postRequest(
                    '/vip/charge',
                    {vipId: vipCardId, amount: Number($('#userMember-amount').val())},
                    function (res) {
                        $('#buyModal').modal('hide');
                        alert("充值成功");
                        getVIP();
                    },
                    function (error) {
                        alert(error);
                    });
            }
        } else {
            alert("银行卡号或密码错误");
        }
    }
}

function validateForm() {
    var isValidate = true;
    if (!$('#userMember-cardNum').val()) {
        isValidate = false;
        $('#userMember-cardNum').parent('.form-group').addClass('has-error');
        $('#userMember-cardNum-error').css("visibility", "visible");
    }
    if (!$('#userMember-cardPwd').val()) {
        isValidate = false;
        $('#userMember-cardPwd').parent('.form-group').addClass('has-error');
        $('#userMember-cardPwd-error').css("visibility", "visible");
    }
    if (!isBuyState && (!$('#userMember-amount').val() || ($('#userMember-amount').val()) <= 0)) {
        isValidate = false;
        $('#userMember-amount').parent('.form-group').addClass('has-error');
        $('#userMember-amount-error').css("visibility", "visible");
    }
    return isValidate;
}

function getCoupon() {
    getRequest(
        '/coupon/' + sessionStorage.getItem('id') + '/get',
        function (res) {
            if (res.success) {
                var couponList = res.content;
                var couponListContent = '';
                for (let coupon of couponList) {
                    couponListContent += '<div class="col-md-6 coupon-wrapper"><div class="coupon"><div class="content">' +
                        '<div class="col-md-8 left">' +
                        '<div class="name">' +
                        coupon.name +
                        '</div>' +
                        '<div class="description">' +
                        coupon.description +
                        '</div>' +
                        '<div class="price">' +
                        '满' + coupon.targetAmount + '减' + coupon.discountAmount +
                        '</div>' +
                        '</div>' +
                        '<div class="col-md-4 right">' +
                        '<div>有效日期：</div>' +
                        '<div>' + formatDate(coupon.startTime) + ' ~ ' + formatDate(coupon.endTime) + '</div>' +
                        '</div></div></div></div>';
                }
                $('#coupon-list').html(couponListContent);

            }
        },
        function (error) {
            alert(error);
        });
}
function formatDate(date) {
    return date.substring(5, 10).replace("-", ".");
}
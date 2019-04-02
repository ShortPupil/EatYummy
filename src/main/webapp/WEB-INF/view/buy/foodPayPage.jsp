<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_orderPay.css" rel="stylesheet"/>
    <title>Yummy! - 网上支付</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <img src="${pageContext.request.contextPath}/static/image/logo.png">
        </div>
    </div>
</nav>

<div class="content">
    <div class="order_div">
        <c:choose>
            <c:when test="${fn:length(requestScope.foodorder.foodorderitemsById)==1}">
                <div class="order_name">
                    <span>${requestScope.foodorder.foodorderitemsById[0].foodByFoodId.name}</span>
                </div>
                <div class="order_shop_name">
                    <span>餐厅名称：${requestScope.foodorder.restaurantByResId.name}</span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="order_name">
                    <span>合并订单：${fn:length(requestScope.foodorder.foodorderitemsById)}笔</span>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="order_price">
            <span>需付款：</span>
            <span class="price_value">${requestScope.orderTotalPrice}</span>
            <span class="price_unit">元</span>
        </div>
        <div>
            <span>您的Yummy!账号余额为</span>
            <span class="price_value">${requestScope.account.balance}</span>
            <span class="price_unit">元</span>
        </div>
    </div>
    <div class="order_pay_div">
        <script>
            function pay() {
                $.ajax({
                    url: "/order/pay/${requestScope.foodorder.id}",
                    type: "PUT",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("订单处理异常，请稍候再试！");
                        }
                        location.href = data.url;
                    },
                    beforeSend: function () {

                    }
                });
            }
        </script>
        <a class="order_pay_btn" href="javascript:void(0)" onclick="pay()">确认支付</a>
    </div>
</div>

<%@include file="include/footer.jsp" %>
</body>

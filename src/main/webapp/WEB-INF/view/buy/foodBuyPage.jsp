<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta name="baidu-site-verification" content="zyu2elKF1h">
  <meta name="robots" content="noodp">

  <style type="text/css">
    /*删除高德地图的logo*/
    .amap-logo {
        right: 0 !important;
        left: auto !important;
        display: none;
    }
    amap-copyright {
        right: 70px !important;
        left: auto !important;

    }
    </style>

    <script src="${pageContext.request.contextPath}/static/js/fore/fore_productBuy.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_productBuyPage.css" rel="stylesheet"/>
    <title>Yummy! ——确认订单</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>
<div class="headerLayout">
    <div class="headerContext">
        <ol class="header-extra">
            <li class="step-done">
                <div class="step-name">下单</div>
                <div class="step-no_first"></div>
            </li>
            <li class="step-no">
                <div class="step-name">付款</div>
                <div class="step-no">2</div>
            </li>
            <li class="step-no">
                <div class="step-name">确认收货</div>
                <div class="step-no">3</div>
            </li>
            <li class="step-no">
                <div class="step-name">评价（未开通）</div>
                <div class="step-no_last">4</div>
            </li>
        </ol>
    </div>
</div>
<div class="content">
    <div class="order_address">
        <h2>输入收货信息</h2>
        <label for="address" id="label_details_address">地址选择</label><span class="mustValue">*</span>
        <select id="address" name="address">
            <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                <option value=${address.id}>${address.localAddress}</option>
            </c:forEach>
        </select>
        <div class="br"></div>
        <label for="input_order_receiver" id="label_order_receiver">收货人姓名</label><span class="mustValue">*</span>
        <input id="input_order_receiver" type="text" value="${requestScope.receiver}" maxlength="20"/>
        <div class="br"></div>
        <label for="input_order_phone" id="label_order_phone">手机号码</label><span class="mustValue">*</span>
        <input id="input_order_phone" type="text" value="${requestScope.phonenumber}" maxlength="11"/>
    </div>
    <div class="order_info">
        <h2>确认订单信息</h2>
        <table class="table_order_orderItem">
            <thead>
            <tr>
                <th>订单</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orderItemList}" var="orderItem" varStatus="i">
                <tr class="tr_shop">
                    <td><span class="span_shopTitle">餐厅：</span><span
                            class="span_shopName">${orderItem.foodorderByOrderId.restaurantByResId.name}</span>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="tr_product_info">
                    <td><img
                            src="${orderItem.foodByFoodId.picture}"
                            style="width: 50px;height: 50px;"/><span class="span_product_name"><a
                            href="${pageContext.request.contextPath}/food/${orderItem.foodByFoodId.id}">${orderItem.foodByFoodId.name}</a></span>
                    </td>
                    <td><span
                            class="span_product_sale_price">${orderItem.foodByFoodId.price}</span>
                    </td>
                    <td><span class="span_productOrderItem_number">${orderItem.number}</span></td>
                    <td><span class="span_productOrderItem_price">￥${orderItem.price}</span></td>
                </tr>
                <tr class="tr_orderCount">
                    <td colspan="3"></td>
                    <td><span class="span_price_name">单项价格</span><span
                            class="span_price_value">￥${orderItem.price*orderItem.number}</span></td>
                </tr>
                <tr class="tr_userMessage">
                    <td colspan="4"><input type="hidden" class="input_orderItem_id"
                                           value="${orderItem.id}"/>
                </tr>
            </c:forEach>
            <tr>根据促销策略，您可优惠￥${discount}</tr>
            </tbody>
        </table>
    </div>
    <div class="order_count_div">
        <div class="order_count_div_main">
            <div class="order_count_div_content">
                <h3 class="order_count_div_price">
                    <span class="order-title">应付：</span>
                    <span class="realPay-price_unit">￥</span>
                    <span class="realPay-price">${orderTotalPrice}</span>
                </h3>
                <h1 class="order_count_div_price">
                    <span class="order-title">实付：</span>
                    <span class="realPay-price_unit">￥</span>
                    <span class="realPay-price">${orderTotalPrice-discount}</span>
                </h1>
                <h1 class="order_count_div_phone">
                    <span class="order-title">收货人：</span><span
                        class="order-value user-name"></span><span
                        class="order-value user-phone">${requestScope.receiver}</span>
                </h1>
            </div>
        </div>
    </div>
    <script>
        function payOne() {
            var myselect=document.getElementById("address");
            var index=myselect.selectedIndex ;
            var foodorder_address = myselect.options[index].value;
            alert(foodorder_address);

            var foodorder_receiver = $.trim($("#input_order_receiver").val());
            var foodorder_phonenumber = $.trim($("#input_order_phone").val());
            var orderItem_product_id = parseInt('${requestScope.orderItemList[0].foodByFoodId.id}');
            var orderItem_number = parseInt('${requestScope.orderItemList[0].number}');

            var yn = true;
            if (foodorder_address === "") {
                styleUtil.specialBasicErrorShow($("#label_details_address"));
                yn = false;
            }
            if (foodorder_receiver === "") {
                styleUtil.specialBasicErrorShow($("#label_order_receiver"));
                yn = false;
            }
            var re = /^1[3|4|5|7|8]\d{9}$/;
            if (!re.test(foodorder_phonenumber)) {
                styleUtil.specialBasicErrorShow($("#label_order_phone"));
                yn = false;
            }
            if (!yn) {
                window.scrollTo(0, 0);
                return false;
            }
            $.ajax({
                url: "/order",
                type: "POST",
                data: {
                    "foodorder_address": foodorder_address,
                    "foodorder_receiver": foodorder_receiver,
                    "foodorder_phonenumber": foodorder_phonenumber,
                    "orderItem_product_id": orderItem_product_id,
                    "orderItem_number": orderItem_number
                },
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        location.href = data.url;
                    } else {
                        alert("订单创建失败，请稍后再试！");
                        location.reload(true);
                    }
                },
                beforeSend: function () {

                },
                error: function () {
                    alert("订单提交出现问题，请重新提交！");
                    location.reload(true);
                }
            });
        }

        function payList() {
            var myselect=document.getElementById("address");
            var index=myselect.selectedIndex ;
            var foodorder_address = myselect.options[index].value;

            var foodorder_receiver = $.trim($("#input_order_receiver").val());
            var foodorder_phonenumber = $.trim($("#input_order_phone").val());

            var yn = true;
            if (foodorder_address === "") {
                styleUtil.specialBasicErrorShow($("#label_details_address"));
                yn = false;
            }
            if (foodorder_receiver === "") {
                styleUtil.specialBasicErrorShow($("#label_order_receiver"));
                yn = false;
            }
            var re = /^1[3|4|5|7|8]\d{9}$/;
            if (!re.test(foodorder_phonenumber)) {
                styleUtil.specialBasicErrorShow($("#label_order_phone"));
                yn = false;
            }
            if (!yn) {
                window.scrollTo(0, 0);
                return false;
            }

            //var foodorderitem_id = [];
            var tr = $(".tr_userMessage");
            var orderItem_id;
            tr.each(function () {
                orderItem_id = $(this).find(".input_orderItem_id").val();
                if (isNaN(orderItem_id) || orderItem_id === "") {
                    location.reload(true);
                    return false;
                }
                //orderItemMap.push(orderItem_id);
                //orderItemMap[orderItem_id] = $(this).find(".input_userMessage").val();
                alert(orderItem_id);
            });
            $.ajax({
                url: "/order/list",
                type: "POST",
                data: {
                    "foodorder_address": foodorder_address,
                    "foodorder_receiver": foodorder_receiver,
                    "foodorder_phonenumber": foodorder_phonenumber,
                    "foodorderitem_id": orderItem_id
                },
                datatype: "json",
                traditional: true,
                success: function (data) {
                    if (data.success) {
                        location.href = data.url;
                        return true;
                    } else {
                        alert("订单创建失败，请稍后再试！");
                        location.reload(true);
                    }
                },
                beforeSend: function () {
                }
            });
        }
    </script>
    <div class="order_info_last">
        <c:choose>
            <c:when test="${requestScope.orderItemList[0].id != null}">
                <a href="javascript:void(0)" title="提交订单" class="go-btn" onclick="payList()">提交订单</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:void(0)" title="提交订单" class="go-btn" onclick="payOne()">提交订单</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@include file="include/footer.jsp" %>
<div class="loader"></div>
</body>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_orderList.css" rel="stylesheet"/>
    <title>Yummy!——订单列表</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="Logo">
            <a href="${pageContext.request.contextPath}">
                <img src="${pageContext.request.contextPath}/static/image/logo_4.png">
            </a>
        </div>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/food" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="product_name" placeholder="搜索Yummy!"
                           maxlength="50">
                    <input type="submit" value="觅  食" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/food?category_id=${category.id}">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="content">
    <ul class="tabs_ul">
        <li <c:if test="${requestScope.status == null}">class="tab_select"</c:if>>
            <a href="${pageContext.request.contextPath}/order">所有订单</a></li>
    </ul>
    <table class="table_orderList">
        <thead>
        <tr>
            <th>食品</th>
            <th width="80px">单价</th>
            <th width="80px">数量</th>
            <th width="140px">实付款</th>
            <th width="140px">交易状态</th>
            <th width="140px">其他操作</th>
        </tr>
        </thead>
        <c:choose>
            <c:when test="${requestScope.foodOrderList != null && fn:length(requestScope.foodOrderList)>0}">
                <c:forEach items="${requestScope.foodOrderList}" var="foodorder">
                    <tbody>
                    <tr class="tr_order_info">
                        <td colspan="6">
                            <span class="span_order_code_title">订单号——</span>
                            <span class="span_order_code">${foodorder.id}</span>
                            <span class="span_pay_date">付款时间 ${foodorder.payDate}</span>
                        </td>
                    </tr>
                    <c:forEach items="${foodorder.foodorderitemsById}" var="foodorderitem" varStatus="i">
                        <tr class="tr_orderItem_info">
                            <td><img class="orderItem_product_image"
                                     src="${foodorderitem.foodByFoodId.picture}"
                                     style="width: 80px;height: 80px;"/><span class="orderItem_product_name"><a
                                    href="${pageContext.request.contextPath}/food/${foodorderitem.foodByFoodId.id}">${foodorderitem.foodByFoodId.name}</a></span>
                            </td>
                            <td>
                                <span class="orderItem_product_price">￥${foodorderitem.price}</span>
                            </td>
                            <td>
                                <span class="orderItem_product_number">${foodorderitem.number}</span>
                            </td>
                            <td>
                                <span class="orderItem_product_realPrice">￥${foodorderitem.price*foodorderitem.number}</span>
                            </td>
                            <c:if test="${i.count == 1}">
                                <c:choose>
                                    <c:when test="${foodorder.status==1}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <span title="购物车中">购物车中</span>
                                    </c:when>
                                    <c:when test="${foodorder.status==2}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <span title="等待付款">等待付款</span>
                                        </td>
                                        <td rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <a class="order_btn pay_btn"
                                               href="${pageContext.request.contextPath}/order/pay/${foodorder.id}">立即付款</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${foodorder.status==3}">
                                        <td rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <span class="span_order_status" title="卖家已发货，等待买家确认">等待买家收货</span><br>
                                            <bean:write name="${foodorder.needTime/60}" format="0.00"/>

                                            <span class="span_order_status" title="送达时间" format="0.00">预计还需${foodorder.needTime/60}分钟送达</span>
                                        </td>
                                        <td class="td_order_content" rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <script>
                                                function confirm() {
                                                    $.ajax({
                                                        url: "/order/confirm/${foodorder.id}",
                                                        type: "PUT",
                                                        data: null,
                                                        dataType: "json",
                                                        success: function (data) {
                                                            if (data.success !== true) {
                                                                alert("订单处理异常，请稍候再试！");
                                                                location.href = "/order";
                                                            }
                                                            location.href = "/order";
                                                        },
                                                        beforeSend: function () {

                                                        },
                                                        error: function () {
                                                            alert("订单确认送达出现问题！");
                                                            location.href = "/order";
                                                        }
                                                    });
                                                }
                                            </script>
                                            <a class="order_btn confirm_btn" href="javascript:void(0)" onclick="confirm()">确认送达</a>
                                            <!--a class="order_btn confirm_btn"
                                               href="${pageContext.request.contextPath}/order/confirm/${foodorder.id}">确认送达</a-->
                                        </td>
                                    </c:when>

                                    <c:when test="${foodorder.status==4}">
                                        <td rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <span class="span_order_status" title="交易成功">交易成功</span>
                                        </td>
                                    </c:when>
                                    <c:when test="${foodorder.status==0}">
                                        <td rowspan="${fn:length(requestScope.foodOrderList)}">
                                            <span class="td_error" title="交易关闭">交易关闭</span>
                                        </td>
                                        <td rowspan="${fn:length(requestScope.foodOrderList)}">
                                        </td>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr>
                    <td colspan="6" class="no_search_result">
                        <span class="error_msg">没有相关订单，请尝试其他搜索条件。</span>
                    </td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
</div>


<%@include file="include/footer.jsp" %>
</body>

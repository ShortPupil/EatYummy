<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_foodBuyCart.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_foodBuyCartPage.css" rel="stylesheet"/>
    <title>Yummy! - 购物车</title>
    <script>

        function removeItem(foodorderitemId) {
            if (isNaN(foodorderitemId) || foodorderitemId === null) {
                return;
            }
            $.ajax({
                url: "/cart/deleteItem",
                type: "POST",
                data: {
                    "foodorderitemId": foodorderitemId
                },
                dataType: "json",
                success: function (data) {
                    if (data.success !== true) {
                        alert("购物车商品删除异常，请稍候再试！");
                    }
                    location.href = "/cart";
                },
                beforeSend: function () {

                }
            });
        }
    </script>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="Logo">
            <a href="${pageContext.request.contextPath}">
                <img src="${pageContext.request.contextPath}/static/image/logo_4.png">
            </a>
            <a href="${pageContext.request.contextPath}">
                <span class="span_tmallBuyCar">Yummy!</span>
            </a>
        </div>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/food" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="food_name" placeholder="搜索 Yummy!"
                           value="${requestScope.searchValue}" maxlength="50">
                    <input type="submit" value="觅 食" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category">
                    <li data-toggle="${category.id}" data-status="">
                        <a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a>
                        <div class="banner_div" name="${category.name}"></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="content">
    <c:choose>
        <c:when test="${fn:length(requestScope.foodorderitemList)==0}">
            <div id="crumbs">
                <span class="cart-tip">购物车帮您一次性完成批量购买与付款，付款更简单！</span>
            </div>
            <div id="empty">
                <h2>您的购物车还是空空如也！您可以</h2>
                <ul>
                    <li><a href="${pageContext.request.contextPath}">到处逛逛</a></li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div id="J_FilterBar">
                <ul id="J_CartSwitch">
                    <li>
                        <a href="${pageContext.request.contextPath}/cart" class="J_MakePoint">
                            <em>全部商品</em>
                            <span class="number">${requestScope.orderItemTotal}</span>
                        </a>
                    </li>
                </ul>
                <div class="wrap-line">
                    <div class="floater"></div>
                </div>
            </div>
            <table id="J_CartMain">
                <thead>
                <tr>
                    <th width="180px"><span>店家</span></th>
                    <th class="selectAll_th"><input type="checkbox" class="cbx_select" id="cbx_select_all"><label
                            for="cbx_select_all">全 选</label></th>
                    <th width="210px" class="productInfo_th"><span>餐点信息</span></th>
                    <th width="180px"><span>单价</span></th>
                    <th width="180px"><span>数量</span></th>
                    <th width="180px"><span>金额</span></th>
                    <th width="120px"><span>操作</span></th>
                    <th hidden>ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.foodorderitemList}" var="foodorderitem">

                    <tr class="orderItem_info">
                        <td>
                            <a>${foodorderitem.foodByFoodId.restaurantByResId.name}</a>
                        </td>
                        <td class="tbody_checkbox">

                            <input type="checkbox" class="cbx_select"
                                   id="cbx_orderItem_select_${foodorderitem.id}"
                                   name="orderItem_id">
                            <label for="cbx_orderItem_select_${foodorderitem.id}" ></label>
                        </td>

                        <td>
                            <img class="orderItem_product_image"
                                 src="${foodorderitem.foodByFoodId.picture}"
                                 style="width: 80px;height: 80px;"/>
                            <span class="orderItem_product_name">
                                <a href="${pageContext.request.contextPath}/food/${foodorderitem.foodId}">${foodorderitem.foodByFoodId.name}</a>
                            </span>
                        </td>
                        <td>
                            <span class="orderItem_product_price" >￥${foodorderitem.price}</span>
                        </td>
                        <td>
                            <div class="item_amount">
                                <a href="javascript:void(0)" onclick="up(this)" class="J_Minus
                                <c:if test="${orderItemTotal<=1}">
                                    no_minus
                                </c:if>">-</a>
                                <input type="text" value="${foodorderitem.number}"/>
                                <a href="javascript:void(0)" onclick="down(this)" class="J_Plus">+</a>
                            </div>
                        </td>
                        <td>
                            <span class="orderItem_product_realPrice">￥${foodorderitem.price * foodorderitem.number}</span>
                        </td>
                        <td><a href="javascript:void(0)" onclick="removeItem('${foodorderitem.id}')"
                               class="remove_order">删除</a></td>
                        <td>
                            <div class="item_id">
                                <input type="hidden" class="input_orderItem" name="${foodorderitem.id} value="${foodorderitem.id}""/>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="J_FloatBar">
                <div id="J_SelectAll2">
                    <div class="cart_checkbox">
                        <input class="J_checkboxShop" id="J_SelectAllCbx2" type="checkbox" value="true"/>
                        <label for="J_SelectAllCbx2" title="勾选购物车内所有食品"></label>
                    </div>
                    <span class="span_selectAll">全选</span>
                </div>

                <div class="float-bar-right">
                    <div id="J_ShowSelectedItems">
                        <span class="txt">已选食品</span>
                        <em id="J_SelectedItemsCount">0</em>
                        <span class="txt">件</span>
                    </div>
                    <div class="price_sum">
                        <span class="txt">合计:</span>
                        <strong class="price">
                            <em id="J_Total">
                                <span class="total_symbol">&nbsp;￥</span>
                                <span class="total_value"> 0.00</span>
                            </em>
                        </strong>
                    </div>
                    <div class="btn_area">
                        <a href="javascript:void(0)" id="J_Go" onclick="create(this)">
                            <span>结&nbsp;&nbsp;&nbsp;算</span>
                        </a>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="include/footer.jsp" %>
</body>

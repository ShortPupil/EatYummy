<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_login.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_foodDetails.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_foodDetails.css" rel="stylesheet">
    <title>${requestScope.food.name}-Yummy!</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <a href="${pageContext.request.contextPath}">
            <img
                src="${pageContext.request.contextPath}/static/image/logo_4.png">
        </a>
        <span class="shopNameHeader">${requestScope.restaurant.name}</span>
        <input id="tid" type="hidden" value="${requestScope.product.product_category.id}"/>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/food" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="food_name" placeholder="搜索 Yummy!"
                           maxlength="50">
                    <input type="submit" value="觅食" class="searchTmall">
                </div>
                <input type="submit" value="搜本店" class="searchShop">
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>

<div class="context">

    <div class="foodImg">
        <img src="${requestScope.food.picture}" />
    </div>
    <div class="context_info">
        <div class="context_info_name_div">
            <p class="context_info_name">${requestScope.food.name}</p>
            <span class="context_info_title">${requestScope.food.name}</span>
        </div>
        <div class="context_info_main">
            <div class="context_info_main_ad">
                <img src="${pageContext.request.contextPath}/static/image/logo_3.png">
                <span>Yummy!优惠菜品</span>
            </div>

            <dl class="context_price_panel">
                <dt>价格</dt>
                <dd><em>¥</em><span>${requestScope.food.price}</span></dd>
            </dl>
            <dl class="context_promotePrice_panel">
                <dt>现价</dt>
                <dd><em>¥</em><span>${requestScope.food.price}</span></dd>
            </dl>
        </div>
        <ul class="context_other_panel">
            <li>总销量<span><c:choose><c:when
                    test="${requestScope.product.product_sale_count != null}">${requestScope.product.product_sale_count}</c:when><c:otherwise>0</c:otherwise></c:choose></span>
            </li>
            <li class="tmall_points">获Yummy!积分<span><fmt:formatNumber type="number"
                                                                  value="${requestScope.food.price/10}"
                                                                  maxFractionDigits="0"/></span></li>
        </ul>
        <dl class="context_info_member">
            <dt>数量</dt>
            <dd>
                <input type="text" value="1" maxlength="8" title="请输入购买量" class="context_buymember">
                <input type="hidden" id="stock" value="1000">
                <span class="amount-btn">
                    <img src="${pageContext.request.contextPath}/static/image/buy/details/up.png"
                         class="amount_value_up">
                    <img src="${pageContext.request.contextPath}/static/image/buy/details/down.png"
                         class="amount_value-down">
                </span>
                <span class="amount_unit">件</span>
                <em>剩余库存<span>${requestScope.food.amount}</span>件</em>
            </dd>
        </dl>
        <div class="context_buy">
            <script>
                $(function () {
                    //点击加入购物车按钮时
                    $(".context_buyCar_form").submit(function () {
                        if ('${sessionScope.memberId}' === "") {
                            return false;
                        }
                        var number = $.trim($(".context_buymember").val());
                        alert(number);

                            $.ajax({
                                url: "/orderItem/create/${requestScope.food.id}",
                                type: "POST",
                                data: {"food_number": number},
                                dataType: "json",
                                success: function (data) {
                                    if (data.success) {
                                        $(".msg").stop(true, true).animate({
                                            opacity: 1
                                        }, 550, function () {
                                            $(".msg").animate({
                                                opacity: 0
                                            }, 1500);
                                        });
                                    } else {
                                        alert("加入购物车失败，请稍后再试！");
                                    }
                                },
                                beforeSend: function () {

                                },
                                error: function () {
                                    alert("加入购物车失败，请稍后再试！");
                                }
                            });
                            //return false;

                    });
                });
            </script>

            <form method="get" class="context_buyCar_form">
                <input class="context_addBuyCar" type="submit" value="加入购物车"/>
            </form>
        </div>
        <div class="context_clear">
            <span>服务承诺</span>
            <a href="#">食品安全保障</a>
            <a href="#">极速退款</a>
        </div>
    </div>
    <div class="context_ul">
        <div class="context_ul_head">
            <span>店家其他商品</span>
        </div>
        <div class="context_ul_goodsList">
            <ul>

                <c:forEach items="${requestScope.restaurantFoodList}" var="restaurantFood">
                    <li class="context_ul_main">
                        <div class="context_ul_img">
                            <a href="/food/${restaurantFood.id}">
                                <img src="${restaurantFood.picture}">
                            </a>
                            <p>¥${restaurantFood.price}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <!--input type="hidden" id="guessNumber" value="${requestScope.guessNumber}"-->
        </div>
        <ul class="context_ul_trigger">
            <li class="ul_trigger_up"><a href="#"></a></li>
            <li class="ul_trigger_down"><a href="#"></a></li>
        </ul>
    </div>
</div>
<div class="mainwrap">
    <div class="J_TabBarBox">
        <ul>
            <li class="J_GoodsDetails">
                <a href="javascript:void(0)" class="detailsClick" onclick="getDetailsPage(this,'J_details')">商品详情</a>
            </li>
            <li class="J_GoodsReviews">
                <a href="javascript:void(0)"
                   onclick="getDetailsPage(this,'J_reviews')">累计评价（未实现评价功能）<span>${requestScope.product.product_review_count}</span></a>
            </li>
        </ul>
    </div>
    <div class="J_choose">
        <%@include file="include/J_details.jsp" %>
        <%@include file="include/J_review.jsp" %>
    </div>
    <div class="J_img">
        <c:forEach items="${requestScope.product.detailProductImageList}" var="image">
            <img src="${requestScope.product.pictrue}"/>
        </c:forEach>
    </div>
</div>
<div class="msg">
    <span>商品已添加</span>
</div>

<%@ include file="include/footer.jsp" %>
</body>

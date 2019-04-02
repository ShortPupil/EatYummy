<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/jquery-color-2.1.2.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_home.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_home.css" rel="stylesheet"/>
    <title>Yummy! — 美味到家</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>

<nav>
    <div class="header">
        <img src="${pageContext.request.contextPath}/static/image/logo_2.png"/>
        <div class="mallSearch">
            <form action="${pageContext.request.contextPath}/food" method="get">
                <div class="mallSearch-input">
                    <input class="header_search_input" type="text" name="food_name" placeholder="搜索 Yummy!"
                           maxlength="50">
                    <input class="header_search_button" type="submit" value="觅食">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.restaurantList}" var="restaurant" varStatus="i">
                    <c:if test="${i.index<9}">
                        <li><a href="${pageContext.request.contextPath}/food?restaurant_id=${restaurant.id}"
                                <c:if
                                        test="${i.index % 2 != 0}"> style="color: #FF0036"</c:if>>${fn:substring(restaurant.name,0,fn:indexOf(restaurant.name,' /'))}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>

    </div>
    <div class="home_nav">
        <div class="home_nav_title">
            <img src="${pageContext.request.contextPath}/static/image/buy/home/header_nav_title.png">
            <span>分类</span>
        </div>

    </div>
</nav>


<div class="banner">
    <c:forEach var="food" items="${requestScope.specialFoodList}" varStatus="i">
        <img src="${pageContext.request.contextPath}/static/image/buy/home/stategy_${food.id}.jpg"
             name="${food.id}" id="banner${i.count}"
             <c:if test="${i.count == 1}">style="display: block;"</c:if> />
    </c:forEach>

    <c:forEach var="food" items="${requestScope.specialFoodList}" varStatus="i">
        <img src="${pageContext.request.contextPath}/static/image/buy/home/stategy_${food.id}.jpg"
             name="${food.id}" id="banner${i.count}"
             <c:if test="${i.count == 1}">style="display: block;"</c:if> />
    </c:forEach>
</div>
<div class="banner_main">
    <ul class="banner_nav">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <li data-toggle="${category.id}" data-status="">
                <a href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a>
                <div class="banner_div" name="${category.name}">
                </div>
            </li>
        </c:forEach>
    </ul>
    <ul class="banner_slider">
        <li id="slider_1" style="background: rgba(255,255,255,0.4)"></li>
        <li id="slider_2"></li>
        <li id="slider_3"></li>
        <li id="slider_4"></li>
        <li id="slider_5"></li>
        <li id="slider_6"></li>
    </ul>
    <a href="#"></a>
</div>
<div class="banner_do">
    <div></div>
    <div class="banner_goods">
        <c:forEach items="${requestScope.restaurantList}" var="restaurant">
            <c:if test="${fn:length(restaurant.foodsById)>0}">
                <div class="banner_goods_type">
                    <div class="banner_goods_title">
                        <span></span>
                        <p>${restaurant.name}</p>
                    </div>
                    <div class="banner_restaurant">
                        <a href="${pageContext.request.contextPath}/restaurant/${restaurant.id}" class="banner_restauant_link"></a>
                        <img class="banner_goods_show" src="${restaurant.picture}" class="banner_restauant_img">

                    </div>
                    <div class="banner_goods_items">
                        <c:forEach items="${restaurant.foodsById}" var="food" varStatus="i">
                            <c:if test="${i.index<8 && food.type != 0}">
                                <div class="banner_goods_item">
                                    <a href="${pageContext.request.contextPath}/food/${food.id}" class="goods_link"></a>
                                    <img src="${food.picture}">
                                    <a href="${pageContext.request.contextPath}/food/${food.id}"
                                       class="goods_name">${food.name}</a>
                                    <span class="goods_price">￥${food.price}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="endDiv"></div>
</div>
<%@ include file="include/footer.jsp" %>
</body>

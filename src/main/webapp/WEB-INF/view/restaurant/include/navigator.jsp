<%@ page contentType="text/html;charset=UTF-8" %>
<nav id="nav_main" class="text_info">
    <span id="txt_home_title" class="nav_text">Yummy!餐厅主页</span>
    <a id="txt_home_nickname" href="${pageContext.request.contextPath}/restaurant">
        <c:choose>
            <c:when test="${requestScope.restaurant.name != null}">
                ${requestScope.restaurant.name}
            </c:when>
            <c:otherwise>
                ${requestScope.restaurant.codenumber}
            </c:otherwise>
        </c:choose>
    </a>
    <img id="img_home_profile_picture"
         src="${pageContext.request.contextPath}/static/image/buy/login/first.png"
         alt="头像" title="头像" width="32px" height="32px">
    <input id="admin_id" type="hidden" value="${requestScope.restaurant.id}"/>
    <a href="${pageContext.request.contextPath}/restaurant/restaurantDetails">餐厅信息中心</a>
    <a href="${pageContext.request.contextPath}/restaurant/login/logout">注销</a>
</nav>

<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fore/fore_nav.css"/>
<script>
    $(function () {
        $(".quick_li").find("li").hover(
            function () {
                $(this).find(".sn_menu").addClass("sn_menu_hover");
                $(this).find(".quick_menu,.quick_qrcode,.quick_DirectPromoDiv,.quick_sitmap_div").css("display", "block");
            }, function () {
                $(this).find(".sn_menu").removeClass("sn_menu_hover");
                $(this).find(".quick_menu,.quick_qrcode,.quick_DirectPromoDiv,.quick_sitmap_div").css("display", "none");
            }
        );
    });
</script>
<div id="nav">
    <div class="nav_main">
        <p id="container_login">
            <c:choose>
                <c:when test="${requestScope.member.name==null}">
                    <em>欢迎来到Yummy!</em>
                    <a href="${pageContext.request.contextPath}/login">请登录</a>
                    <a href="${pageContext.request.contextPath}/register">免费注册</a>
                </c:when>
                <c:otherwise>
                    <em>Hi，</em>
                    <a href="${pageContext.request.contextPath}/memberDetails" class="userName"
                       target="_blank">${requestScope.member.name}</a>
                    <em>&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到Yummy！</em>
                    <a href="${pageContext.request.contextPath}/login/logout">退出</a>
                </c:otherwise>
            </c:choose>
        </p>
        <ul class="quick_li">
            <li class="quick_li_MyTaobao">
                <div class="sn_menu">
                    <a href="${pageContext.request.contextPath}/memberDetails">我的Yummy!<b></b></a>
                    <div class="quick_menu">
                        <a href="${pageContext.request.contextPath}/order">历史订单</a>
                    </div>
                </div>
            </li>
            <li class="quick_li_cart">
                <img src="${pageContext.request.contextPath}/static/image/buy/details/buyCar.png">
                <a href="${pageContext.request.contextPath}/cart">购物车</a>
            </li>
            <li class="quick_li_separator"></li>
            <li class="quick_li_mobile">
                <img src="${pageContext.request.contextPath}/static/image/buy/details/mobile.png">
                <a href="#" title="手机版">（没开通的）手机版</a>
            </li>
            <li class="quick_DirectPromo">
                <div class="sn_menu">
                    <a href="#">餐厅支持<b></b></a>
                    <div class="quick_DirectPromoDiv">
                        <ul>
                            <li>
                                <h3>餐厅：</h3>
                                <a href="${pageContext.request.contextPath}/restaurant">餐厅中心</a>
                                <a href="${pageContext.request.contextPath}/toCopyRight">网站规则</a>
                                <a href="${pageContext.request.contextPath}/restaurant/register">餐厅入驻</a>
                            </li>
                            <li>
                                <h3>帮助：</h3>
                                <a href="">帮助中心</a>
                                <a href="">客服</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
            <li class="quick_sitemap">
                <div class="sn_menu">
                    <a>网站导航<b></b></a>
                    <div class="quick_sitmap_div">
                        <div class="site-hot">
                            <h2>热点推荐<span>Hot</span></h2>
                            <ul>
                                <li><a href="">精品美食</a></li>
                                <li><a href="">早餐</a></li>
                                <li><a href="">午餐</a></li>
                                <li><a href="">晚餐</a></li>
                                <li><a href="">夜宵</a></li>
                                <li><a href="">下午茶</a></li>
                            </ul>
                        </div>
                        <div class="site-brand">
                            <h2>品牌<span>Brand</span></h2>
                            <ul>
                                <li><a href="">星巴克</a></li>
                                <li><a href="">老娘舅</a></li>
                                <li><a href="">更多品牌</a></li>
                            </ul>
                        </div>
                        <div class="site-help">
                            <h2>服务指南<span>Help</span></h2>
                            <ul>
                                <li><a href="">帮助中心</a></li>
                                <li><a href="">食品安全保障</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

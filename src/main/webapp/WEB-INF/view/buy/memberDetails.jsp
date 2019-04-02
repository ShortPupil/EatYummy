<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_memberDatiles.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_memberDatiles.css" rel="stylesheet">

    <title>Yummy - 会员中心</title>
    <style rel="stylesheet">
        #baseNavigator {
            padding: 22px 0;
            width: 1190px;
            height: 44px;
            margin: auto;
        }

        #baseNavigator img {
            width: 190px;
            margin-top: 8px;
        }

        #nav {
            width: auto;
            height: 32px;
            font-family: "Microsoft YaHei UI", Tahoma, serif;
            font-size: 12px;
            position: relative !important;
            background: #f2f2f2;
            z-index: 999;
            border-bottom: 1px solid #e5e5e5;
        }
    </style>
</head>
<body>

<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}">
                <span class="span_tmallRegister">个人中心</span>
            </a>
        </div>
    </div>
</nav>

<div class="content">

    <div class="sns-config" id="profile">
        <div>
            <span>个人资料</span>
        </div>
        <div class="sns-main">
            <div id="tips-box">
                <br>
                <label class="font_we">尊敬的会员 </label>
                <b>${requestScope.member.name}</b>，
                <label  class="font_we">请填写真实的资料</label>
                </br>
            </div>
            <form  id="register_form">
                <div class="form-item">
                    <label class="form-label tsl">当前头像：</label>
                    <ul class="details_picList" id="product_single_list">
                        <li class="details_picList_fileUpload">
                            <img src="${requestScope.member.picture}"
                                 onerror="this.src='${pageContext.request.contextPath}/static/image/logo_2.png'"
                                 id="header_image" width="12px" height="12px">
                            <input type="file" onchange="uploadImage(this)" id="user_profile_picture_src" accept="image/*">
                            <input name="user_profile_picture_src" id="user_profile_picture_src_value" type="hidden"/>
                        </li>
                    </ul>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">段位积分：${requestScope.member.levelPoint} </label><p><p>
                    <label class="form-label tsl">可获折扣：${requestScope.level.discount}折</label><p><p>
                    <label class="form-label tsl">当前段位：${requestScope.level.name}！</label>
                    <img src="${requestScope.level.picture}"/>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">名称：</label>
                    <input name="member_name" value="${requestScope.member.name}" id="member_name"
                           class="form-text err-input" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">登录密码：</label>
                    <input name="member_password" type="password" id="member_password" class="form-text err-input"
                           placeholder="请设置登录密码" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">确认密码：</label>
                    <input name="member_password_one" type="password" id="member_password_one" class="form-text err-input"
                           placeholder="请再次输入你的密码" maxlength="20">
                    <span class="form_span"></span>
                </div>

                <div class="form-item">
                    <label class="form-label tsl">手机号码：</label>
                    <input name="phoneNumber" id="phoneNumber" class="form-text err-input"
                           value="${requestScope.member.phoneNumber}" maxlength="20">
                    <span class="form_span"></span>
                </div>

                <div class="form-item">
                    <label class="form-label tsl">首选地址：</label>
                    <input name="preferredAddress" id="preferredAddress" class="form-text err-input"
                           value="${requestScope.member.preferredAddress}" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">更多地址：</label>
                    <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">

                        <input name="address" id="address" class="form-text err-input"
                               value="${address.localAddress}" maxlength="20">
                    </c:forEach>

                    <span class="form_span"></span>
                    <button onclick="show('addressLay')">新增地址</button>
                </div>
                <div class="form-item">
                    <input type="submit" id="register_sub" class="btns btn-large tsl" value="更 新"/>
                </div>
                <div class="form-item">
                    <input type="submit" id="delete" class="btns2" value="注销 账号"/>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/static/css/fore/fore_foot_special.css" rel="stylesheet"/>
</body>


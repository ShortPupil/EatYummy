<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_login.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_login.css" rel="stylesheet">
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
    <title>登录Yummy!</title>
</head>
<body>

<div class="content">
    <div class="contentMain"><img width="120px" src="${pageContext.request.contextPath}/static/image/logo.png"/></div>
    <div class="loginDiv">
        <div class="loginSwitch" id="loginSwitch"></div>
        <div class="loginMessage">
            <div class="loginMessageMain">
                <div class="poptip-arrow"><em></em><span></span></div>
                <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/scan-safe.png"/><span>扫码登录更安全</span>
            </div>
        </div>
        <div class="pwdLogin">
            <span class="loginTitle">密码登录</span>
            <form method="post" class="loginForm">
                <div class="loginInputDiv">
                    <label for="email" class="loginLabel"><img
                            src="${pageContext.request.contextPath}/static/image/buy/login/first.png"
                            width="38px" height="39px" title="邮箱"/></label>
                    <input type="text" name="email" id="email" class="loginInput" placeholder="邮箱">
                </div>
                <div class="loginInputDiv">
                    <label for="password" class="loginLabel"><img
                            src="${pageContext.request.contextPath}/static/image/buy/login/second.png"
                            width="38px" height="39px" title="登录密码"/></label>
                    <input type="password" name="password" id="password" class="loginInput">
                </div>
                <input type="submit" class="loginButton" value="登 录">
            </form>
            <div class="loginLinks">
                <a href="#">忘记密码(没搞)</a>
                <a href="${pageContext.request.contextPath}/register">免费注册</a>
            </div>
            <div class="error_message">
                <p id="error_message_p"></p>
            </div>
        </div>
        <div class="qrcodeLogin">
            <span class="loginTitle">手机扫码，安全登录</span>
            <div class="qrcodeMain">
                <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/login_qrcode.png"
                     id="qrcodeA"/>
            </div>
            <div class="qrcodeFooter">
                <img src="${pageContext.request.contextPath}/static/images/fore/WebsiteImage/scan_icon2.png">
                <p>打开 扫一扫登录</p>
            </div>
            <div class="loginLinks">

                <a href="JavaScript:void(0)" id="pwdLogin">密码登录</a>
                <a href="${pageContext.request.contextPath}/register">免费注册</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/static/css/fore/fore_foot_special.css" rel="stylesheet"/>
</body>

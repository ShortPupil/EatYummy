<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/sell/sell_login.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/sell/sell_login.css" rel="stylesheet">
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
    <title>商家登录</title>
</head>
<body>

<div class="content">
    <div class="contentMain"><img width="120px" src="${pageContext.request.contextPath}/static/image/logo.png"/></div>
    <div class="loginDiv">
        <div class="pwdLogin">
            <span class="loginTitle">商家登录入口</span>
            <form method="post" class="loginForm">
                <div class="loginInputDiv">
                    <label for="codenumber" class="loginLabel"><img
                            src="${pageContext.request.contextPath}/static/image/buy/login/first.png"
                            width="38px" height="39px" title="商家编号"/></label>
                    <input type="text" name="codenumber" id="codenumber" class="loginInput" placeholder="商家编号">
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
                <a href="${pageContext.request.contextPath}/restaurant/register">商家入驻</a>
            </div>
            <div class="error_message">
                <p id="error_message_p"></p>
            </div>
        </div>
        <div class="qrcodeLogin">
            <div class="loginLinks">

                <a href="JavaScript:void(0)" id="pwdLogin">商家登录入口</a>
                <a href="${pageContext.request.contextPath}/register">免费注册</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/static/css/fore/fore_foot_special.css" rel="stylesheet"/>
</body>

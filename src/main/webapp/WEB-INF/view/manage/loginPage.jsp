<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/static/js/admin/admin_login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/admin_login.css"/>
    <title>Yummy! 管理后台 - 登录</title>
</head>
<body>
<div id="div_background">
    <div id="div_nav">
        <span id="txt_date"></span>
        <span id="txt_peel">换肤</span>
        <ul id="div_peelPanel">
            <li value="url(${pageContext.request.contextPath}/static/image/background/bg-5.png)">
                <img src="${pageContext.request.contextPath}/static/image/background/bg-mini-5.png"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/static/image/background/bg-6.png)">
                <img src="${pageContext.request.contextPath}/static/image/background/bg-mini-6.png"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/static/image/background/bg-7.png)">
                <img src="${pageContext.request.contextPath}/static/image/background/bg-mini-7.png"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/static/image/background/bg-8.png)">
                <img src="${pageContext.request.contextPath}/static/image/background/bg-mini-8.png"/>
            </li>
        </ul>
    </div>
    <div style="text-align:center">
        <img src="${pageContext.request.contextPath}/static/image/logo.png" height="150" width="150" />
    </div>
    <div id="div_main">
        <div id="div_head"><p><span>管理后台</span></p></div>
        <div id="div_content">
            <img id="img_profile_picture"
                 src="${pageContext.request.contextPath}/static/image/background/default_profile_picture-128x128.png"
                 alt="头像" title="头像"
                 onerror="this.src='${pageContext.request.contextPath}/static/images/admin/loginPage/default_profile_picture-128x128.png'"/>
            <form id="form_login">
                <input type="text" class="form-control form_control" placeholder="用户名" id="input_username" title="请输入用户名"/>
                <input type="password" class="form-control form_control" placeholder="密码" id="input_password" title="请输入密码" autocomplete="on">
                <span id="txt_error_msg"></span>
                <input type="button" class="btn btn-danger" id="btn_login" value="登录"/>
            </form>
        </div>
    </div>
</div>
</body>

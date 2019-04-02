<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>

<head>
    <script src="${pageContext.request.contextPath}/static/js/fore/fore_register.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/fore/fore_register.css" rel="stylesheet">
    <title>Yummy! - 注册</title>

    <style rel="stylesheet">
        #nav {
            width: auto;
            height: 32px;
            font-family: "Microsoft YaHei UI", Tahoma, serif;
            font-size: 12px;
            position: relative !important;
            background: #f2e5f0;
            z-index: 999;
            border-bottom: 1px solid #e4dae5;
        }
    </style>
</head>
<body>
<nav>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}">
                <span class="span_tmallRegister">用户注册</span></a>
        </div>
    </div>
</nav>
<div class="content" style="align-content: center">
    <div class="steps">
        <div class="steps_main">
            <span class="steps_tsl">填写账号信息</span>
        </div>
    </div>
    <div class="form-list">
        <div class="form-item">
            <label class="form-label form-label-b tsls">设置会员名</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">用户名：</label>
            <input name="member_name" id="member_name" class="form-text err-input" placeholder="请输入用户名" maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label form-label-b tsls">设置登录密码</label>
            <label class="form-label tsl">登录时验证，保护账号信息</label>
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
            <label class="form-label form-label-b tsls">设置邮箱</label>
            <label class="form-label tsl">登录用邮箱</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">邮箱：</label>
            <input name="member_email" id="member_email" class="form-text err-input"
                    placeholder="输入您的注册邮箱"  maxlength="20">
            <span id="span_email"></span>

            <button type="button" id="send">发送验证码</button>
             <script type="text/javascript">
                 wait = 3;
                 function wait_time(btn) {
                     if (wait == 0) {
                         btn.removeAttribute("disabled");
                         btn.innerText = "发送验证码";
                         wait = 10;
                     } else {
                         btn.setAttribute("disabled", true);
                         btn.innerText = wait+"秒后重新发送";
                         wait--;
                         setTimeout(function(){wait_time(btn);},1000);
                     }
                 }
                 document.getElementById('send').onclick = function(){wait_time(this)};
             </script>
        </div>

        <div class="form-item">
            <label class="form-label tsl">验证码：</label>
            <input name="member_validateCode" id="member_validateCode" class="form-text err-input"
                   placeholder="请输入邮箱收到的验证码" maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            我已阅读并接受
                        <a href="${pageContext.request.contextPath}/toCopyRight"> 各种奇怪的条款 </a><br>
        </div>
        <div class="form-item">
            <input type="button" id="register_sub" class="btns btn-large tsl" value="注 册"/>
        </div>

    </div>
</div>

<%@include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/static/css/fore/fore_foot_special.css" rel="stylesheet"/>
<div class="msg">
    <span>注册成功，跳转到登陆页面</span>
</div>

</body>

$(function () {
    //初始化
    initialCookie();
    initialData();

    /******
     * event
     ******/
    //点击面板main时
    $("#div_main").click(function () {
        var div = $("#div_peelPanel");
        if (div.css("display") === "block") {
            div.slideUp();
        }
    });
    //点击顶部换肤img时
    $("#div_peelPanel").find(">li>img").click(function () {
        var background = $("#div_background");
        var url = $(this).parent("li").attr("value");
        if(url !== null && url !== ""){
            if(url !== background.css("background-image")){
                background.css("background-image", url);
                cookieUtil.setCookie("backgroundImageUrl", url,365);
            }
        }
    });
    //点击顶部换肤标签时
    $("#txt_peel").click(function () {
        var div = $("#div_peelPanel");
        if(div.css("display")==="block"){
            div.slideUp();
        } else {
            div.slideDown();
        }
    });
    //二维码动画
    $("#qrcodeA").hover(
        function () {
            $(this).stop().animate({left: "13px"}, 450, function () {
                $("#qrcodeB").stop().animate({opacity: 1}, 300)
            });
        }
        , function () {
            $("#qrcodeB").css("opacity", "0");
            $(this).stop().animate({left: "80px"}, 450);
        });
    //登录方式切换
    $("#loginSwitch").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        if ($(".pwdLogin").css("display") === "block") {
            $(".pwdLogin").css("display", "none");
            $(".qrcodeLogin").css("display", "block");
            messageSpan.text("密码登录在这里");
            $(this).removeClass("loginSwitch").addClass("loginSwitch_two");
        } else {
            $(".pwdLogin").css("display", "block");
            $(".qrcodeLogin").css("display", "none");
            messageSpan.text("扫码登录(未开通)");
            $(this).removeClass("loginSwitch_two").addClass("loginSwitch");
        }
    });
    $("#pwdLogin").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        $(".pwdLogin").css("display", "block");
        $(".qrcodeLogin").css("display", "none");
        messageSpan.text("扫码登录(未开通)");
        $("#loginSwitch").removeClass("loginSwitch_two").addClass("loginSwitch");
    });
    //登录验证
    $(".loginForm").submit(function () {
        var yn = true;
        $(this).find(":text,:password").each(function () {
            if ($.trim($(this).val()) === "") {
                styleUtil.errorShow($("#error_message_p"), "请输入邮箱和密码！");
                yn = false;
                return yn;
            }
        });
        if (yn) {
            $.ajax({
                type: "POST",
                url: "/login/doLogin",
                data: {"email": $.trim($("#name").val()), "password": $.trim($("#password").val())},
                dataType: "json",
                success: function (data) {
                    $(".loginButton").val("登 录");
                    if (data.success) {
                        location.href = "/";
                    } else {
                        styleUtil.errorShow($("#error_message_p"), "用户名和密码错误！");
                    }
                },
                error: function (data) {
                    $(".loginButton").val("登 录");
                    styleUtil.errorShow($("#error_message_p"), "服务器异常，请刷新页面再试！");
                },
                beforeSend: function () {
                    $(".loginButton").val("正在登录...");
                }
            });
        }
        return false;
    });
    $(".loginForm :text,.loginForm :password").focus(function () {
        styleUtil.errorHide($("#error_message_p"));
    });
});

//初始化Cookie数据
function initialCookie() {
    var url;
    var email;
    if(document.cookie.length>0) {
        email = cookieUtil.getCookie("email");
        url = cookieUtil.getCookie("backgroundImageUrl");
        if(url !== null) {
            $("#div_background").css("background-image", url);
        } else {
            $("#div_background").css("background-image", "url(/static/images/admin/loginPage/background-1.jpg)");
        }
        if(email !== null){
            $("#input_email").val(email);
            getUserProfilePicture(email);
        }
    } else {
        $("#div_background").css("background-image", "url(/static/images/admin/loginPage/background-1.jpg)");
    }
}
//初始化页面数据
function initialData() {
    //顶部时间
    $("#txt_date").text(new Date().toLocaleString());
    setInterval(function () {
        $("#txt_date").text(new Date().toLocaleString());
    }, 1000);
    //表单焦点
    var txt_email = $("#input_email");
    var email = $.trim(txt_email.val());
    if(email !== null && email !== ""){
        $("#input_password").focus();
        return;
    }
    txt_email.focus();
}
//获取用户头像
function getUserProfilePicture(email) {
    if(email !== null && email !== ""){
        $.getJSON("/login/profile_picture",{"email":email},function (data) {
            if(data.success){
                if(data.srcString !== null){
                    $("#img_profile_picture").attr("src", "/static/images/item/adminProfilePicture/" + data.srcString);
                    return true;
                }
            }
        });
    }
    $(".loginForm :text,.loginForm :password").focus(function () {
        styleUtil.errorHide($("#error_message_p"));
    });
}

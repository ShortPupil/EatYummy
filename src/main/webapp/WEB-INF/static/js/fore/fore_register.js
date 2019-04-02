$(function () {


    //用户名input获取光标
    $("#member_name").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入用户名").css("display", "inline-block").css("color", "#00A0E9");
    });
    //密码input获取光标
    $("#member_password").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //再次输入密码input获取光标
    $("#member_password_one").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请再次输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //验证码input获取光标
    $("#member_validateCode").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入验证码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //input离开光标
    $(".form-text").blur(function () {
        $(this).css("border-color", "#cccccc")
            .next().css("display", "none");
    });

    $("#send").click(function () {
        var member_email = $.trim($("input[name=member_email]").val());
        var mail = new RegExp(/^\\w+@(\\w+\\.)+\\w+$/);

        if(member_email == null || member_email === ""){ return false;}

        $.ajax({
            type: "POST",
            url: "/register/sendEmail",
            data: {
                "member_email": member_email
            },
            dataType: "json",
            success: function (data) {
                alert("验证码发送成功，请注意查收。");
            }
        })
    })

    //非空验证
    $("#register_sub").click(function () {
        //用户名
        var member_name = $.trim($("input[name=member_name]").val());
        //密码
        var member_password = $.trim($("input[name=member_password]").val());
        //确认密码
        var member_password_one = $.trim($("input[name=member_password_one]").val());
        //邮箱
        var member_email = $.trim($("input[name=member_email]").val());
        //验证码
        var member_validateCode = $.trim($("input[name=member_validateCode]").val());
        //验证密码的格式 包含数字和英文字母
        var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
        //检验邮箱的格式
        //var mail = new RegExp(/^\\w+@(\\w+\\.)+\\w+$/);
        if (member_name == null || member_name === "") {
            $("#member_name").css("border", "1px solid red")
                .next().text("请输入用户名").css("display", "inline-block").css("color", "red");
            return false;
        } else if (member_password == null || member_password === "") {
            $("#member_password").css("border", "1px solid red")
                .next().text("请输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (member_email == null || member_email === "") {
            $("#member_email").css("border", "1px solid red")
                .next().text("请输入邮箱").css("display", "inline-block").css("color", "red");
            return false;
        }else if (member_password_one == null || member_password_one === "") {
            $("#member_password_one").css("border", "1px solid red")
                .next().text("请重复输入密码").css("display", "inline-block").css("color", "red");
            return false;
        }else if (member_validateCode == null || member_validateCode === "") {
            $("#member_validateCode").css("border", "1px solid red")
                .next().text("请输入验证码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (member_password !== member_password_one) {
            $("#member_password_one").css("border", "1px solid red")
                .next().text("两次输入密码不相同").css("display", "inline-block").css("color", "red");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/register/doRegister",
            data: {
                "member_name": member_name,
                "member_password": member_password,
                "member_email": member_email,
                "member_validateCode": member_validateCode
            },
            dataType: "json",
            success: function (data) {
                if (data.success) {

                    $(".msg").stop(true, true).animate({
                        opacity: 1
                    }, 550, function () {
                        $(".msg").animate({
                            opacity: 0
                        }, 1500, function () {
                            location.href = "/login";
                        });
                    });
                } else {
                    $("#member_email").css("border", "1px solid red")
                        .next().text(data.msg).css("display", "inline-block").css("color", "red");
                }
            },
            error: function (data) {
                location.reload(true);
            },
            beforeSend: function () {
            }
        });
    });
});


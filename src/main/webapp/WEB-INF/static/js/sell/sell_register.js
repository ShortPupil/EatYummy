$(function () {


    //用户名input获取光标
    $("#restaurant_name").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入餐厅名").css("display", "inline-block").css("color", "#00A0E9");
    });
    //密码input获取光标
    $("#restaurant_password").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //再次输入密码input获取光标
    $("#restaurant_password_one").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请再次输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //验证码input获取光标
    $("#restaurant_address").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入餐厅的详细地址").css("display", "inline-block").css("color", "#00A0E9");
    });
    //input离开光标
    $(".form-text").blur(function () {
        $(this).css("border-color", "#cccccc")
            .next().css("display", "none");
    });

    $("#create_account").click(function () {
        $.ajax({
            type: "POST",
            url: "/restaurant/register/createAccount",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $(".msg").stop(true, true).animate({
                        opacity: 1
                    }, 550, function () {
                        $(".msg").animate({
                            opacity: 0
                        }, 1500, function () {
                            location.href = "/restaurant/register";
                        });
                    });
                } else {
                    $("#account").css("border", "1px solid red")
                        .next().text(data.msg).css("display", "inline-block").css("color", "red");
                }
            }
        });
    });

    //非空验证
    $("#register_sub").click(function () {
        //用户名
        var restaurant_name = $.trim($("input[name=restaurant_name]").val());
        //密码
        var restaurant_password = $.trim($("input[name=restaurant_password]").val());
        //确认密码
        var restaurant_password_one = $.trim($("input[name=restaurant_password_one]").val());
        //邮箱
        var restaurant_address = $.trim($("input[name=restaurant_address]").val());
        //类型
        var restaurant_type = $.trim($("input[name=restaurant_type]").val());
        //分类
        var myselect=document.getElementById("restaurant_category");
        var index=myselect.selectedIndex ;
        var restaurant_category = myselect.options[index].value;
        alert(restaurant_category);
        //经纬度
        var tip = document.getElementById("tip");
        var restaurant_coordinate = tip.innerHTML.split("：")[1].split(",")[1];
        //账户
        var account = document.getElementById("account");
        var account_id = account.innerText.split(" ")[1];
        if (restaurant_name == null || restaurant_name === "") {
            $("#restaurant_name").css("border", "1px solid red")
                .next().text("请输入餐厅名").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (restaurant_password == null || restaurant_password === "") {
            $("#restaurant_password").css("border", "1px solid red")
                .next().text("请输入密码").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (restaurant_password_one == null || restaurant_password_one === "") {
            $("#restaurant_password_one").css("border", "1px solid red")
                .next().text("请重复输入密码").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (restaurant_password !== restaurant_password_one) {
            $("#restaurant_password_one").css("border", "1px solid red")
                .next().text("两次输入密码不相同").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (restaurant_coordinate == null || restaurant_coordinate == ""){
            $("restaurant_coordinate").css("border", "1px solid red")
                .next().text("点击右下角定位餐厅位置").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (restaurant_address == null || restaurant_address === "") {
            $("#restaurant_password").css("border", "1px solid red")
                .next().text("请输入本地地址").css("display", "inline-block").css("color", "red");
            return false;
        }  else if (account_id == null || account_id === "") {
            $("#account").css("border", "1px solid red")
                .next().text("请输入创建Yummy!账户").css("display", "inline-block").css("color", "red");
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/restaurant/register/doRegister",
            data: {
                "restaurant_type": restaurant_type,
                "restaurant_category": restaurant_category,
                "restaurant_password": restaurant_password,
                "restaurant_name": restaurant_name,
                "restaurant_address": restaurant_address,
                "restaurant_coordinate": restaurant_coordinate,
                "restaurant_account": account_id
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
                            location.href = "/restaurant/register/result";
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


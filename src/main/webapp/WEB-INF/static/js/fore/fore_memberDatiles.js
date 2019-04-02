function show(idname) {
    var isIE = (document.all) ? true : false;
    var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
    var newbox = document.getElementById(idname);
    newbox.style.zIndex = "9999";
    newbox.style.display = "block"
    newbox.style.position = !isIE6 ? "fixed" : "absolute";
    newbox.style.top = newbox.style.left = "50%";
    newbox.style.marginTop = -newbox.offsetHeight / 2 + "px";
    newbox.style.marginLeft = -newbox.offsetWidth / 2 + "px";
    var jihualayer = document.createElement("div");
    jihualayer.id = "jihualayer";
    jihualayer.style.width = jihualayer.style.height = "100%";
    jihualayer.style.position = !isIE6 ? "fixed" : "absolute";
    jihualayer.style.top = jihualayer.style.left = 0;
    jihualayer.style.backgroundColor = "#000";
    jihualayer.style.zIndex = "9998";
    jihualayer.style.opacity = "0.6";
    document.body.appendChild(jihualayer);
    var sel = document.getElementsByTagName("select");
    for (var i = 0; i < sel.length; i++) {
        sel[i].style.visibility = "hidden";
    }
    function jihualayer_iestyle() {
        jihualayer.style.width = Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth)
            + "px";
        jihualayer.style.height = Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight) +
            "px";
    }
    function newbox_iestyle() {
        newbox.style.marginTop = document.documentElement.scrollTop - newbox.offsetHeight / 2 + "px";
        newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
    }
    if (isIE) { jihualayer.style.filter = "alpha(opacity=60)"; }
    if (isIE6) {
        jihualayer_iestyle()
        newbox_iestyle();
        window.attachEvent("onscroll", function () {
            newbox_iestyle();
        })
        window.attachEvent("onresize", jihualayer_iestyle)
    }
    jihualayer.onclick = function () {
        newbox.style.display = "none"; jihualayer.style.display = "none"; for (var i = 0; i < sel.length; i++) {
            sel[i].style.visibility = "visible";
        }
    }
}


$(function () {

    //非空验证
    $("#register_sub").click(function () {
        //真实姓名
        var member_name = $.trim($("input[name=member_name]").val());
        //密码
        var member_password = $.trim($("input[name=member_password]").val());
        //确认密码
        var member_password_one = $.trim($("input[name=member_password_one]").val());
        //昵称
        var preferredAddress = $.trim($("input[name=preferredAddress]").val());

        if (member_password!=="" && (member_password_one == null || member_password_one === "")) {
            $("#member_password_one").css("border", "1px solid red")
                .next().text("请输入相同密码").css("display", "inline-block").css("color", "red");
            return false;
        }
        $.ajax({type: "POST",
            url: "/doUpdate",
            data: {
                "member_name": member_name,
                "member_password": member_password,
                "preferredAddress": preferredAddress
            },
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    location.href = "/memberDetails";
                } else {
                    styleUtil.errorShow($("#error_message_p"), data.msg);
                }
            },
            beforeSend: function () {
                $(".loginButton").val("正在更新...");
            }
        })
    });

    $("#delete").click(function () {
        $.ajax({
            type: "GET",
            url: "/doDelete",

            dataType: "json",
            success: function (data) {
                if (data.success) {
                    location.href = "/login";
                } else {
                    styleUtil.errorShow($("#error_message_p"), data.msg);
                }
            },
            beforeSend: function () {
                $(".loginButton").val("正在删除...");
            }
        })
    })
});


function updateFood(food_id) {

    var id = food_id;

    var food_name = document.getElementById("food_name_" + id).value;
    var food_price =  document.getElementById("food_price_" + id).value;
    var food_amount =  document.getElementById("food_amount_" + id).value;
    //alert(food_id +" " + food_name+" " + food_price+" " + food_amount);
    $.ajax({
        type: "POST",
        url: "/restaurant/doUpdate",
        data: {
        "food_id": food_id,
            "food_name": food_name,
            "food_price": food_price,
            "food_amount": food_amount
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在更新...");
        }
    })
}

function deleteFood(food_id) {
    $.ajax({
        type: "POST",
        url: "/restaurant/doDelete",
        data: {
            "food_id": food_id
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在删除...");
        }
    })
}

function updateStrategy(strategy_id) {

    var strategy_id = strategy_id;
    var strategy_full = document.getElementById("strategy_full_" + strategy_id).value;
    var strategy_discount =  document.getElementById("strategy_discount_" + strategy_id).value;
    alert(strategy_id +" " + strategy_full+" " + strategy_discount);
    $.ajax({
        type: "POST",
        url: "/restaurant/doUpdateStrategy",
        data: {
            "strategy_id": strategy_id,
            "strategy_full": strategy_full,
            "strategy_discount": strategy_discount
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在更新...");
        }
    })
}

function deleteStrategy(strategy_id) {
    $.ajax({
        type: "POST",
        url: "/restaurant/doDeleteStrategy",
        data: {
            "strategy_id": strategy_id
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在删除...");
        }
    })
}

function showid(idname) {
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

function addFood() {
    var food_name = document.getElementById("food_name").value;
    var food_price = document.getElementById("food_price").value;
    var food_amount = document.getElementById("food_amount").value;
    var food_start_time = document.getElementById("food_start_time").value;
    var food_end_time = document.getElementById("food_end_time").value;
    var food_discription = document.getElementById("food_discription").value;
    //var food_picture = document.getElementById("picture").value;

    if (food_name == null || food_name === "") {
        $("#food_name").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (food_price == null || food_price === "") {
        $("#food_price").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (food_amount == null || food_amount === "") {
        $("#food_amount").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (food_start_time == null || food_start_time === "") {
        $("#food_start_time").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (food_end_time == null || food_end_time === "") {
        $("#food_end_time").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (food_discription == null || food_discription === "") {
        $("#food_discription").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    }

    // 1. 创建一个空的fd对象
    var fd = new FormData();
    $("picture").on("change", function (e) {
        var $this = $(this);
        // 检测文件大小 1024*1024=1048576(1M)
        if (e.target.files[0].size >= 1048576) {
            BJUI.alertmsg("warn", "上传图片大小不得超过1M");
            return false;
        }
        // 预览图片
        var reader = new FileReader();
        reader.onload = (function (file) {
            return function (e) {
                var datainfo = this.result;
                $("#IDcardShow").css({
                    "background": "url(" + datainfo + ") center center no-repeat",
                    "background-size": "cover"
                });
            };
        })(e.target.files[0]);
        reader.readAsDataURL(e.target.files[0]);
        // 添加到fd对象中等待提交
        fd.append("cert_scan", e.target.files[0]);
    });

    $.ajax({
        type: "POST",
        url: "/restaurant/doAdd",
        data: {
            "food_name": food_name,
            "food_price": food_price,
            "food_amount": food_amount,
            "food_start_time": food_start_time,
            "food_end_time": food_end_time,
            "food_discription": food_discription,
            //"food_picture":fd
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在更新...");
        }
    })
}

function addStrategy() {
    var strategy_full = document.getElementById("strategy_full").value;
    var strategy_discount = document.getElementById("strategy_discount").value;

    if (strategy_full == null || strategy_full === "") {
        $("#strategy_full").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    } else if (strategy_discount == null || strategy_discount === "") {
        $("#strategy_discount").css("border", "1px solid red")
            .next().text("请输入").css("display", "inline-block").css("color", "red");
        return false;
    }

    $.ajax({
        type: "POST",
        url: "/restaurant/doAddStrategy",
        data: {
            "strategy_full": strategy_full,
            "strategy_discount": strategy_discount
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                location.href = "/restaurant";
            } else {
                styleUtil.errorShow($("#error_message_p"), data.msg);
            }
        },
        beforeSend: function () {
            $(".loginButton").val("正在更新...");
        }
    })
}

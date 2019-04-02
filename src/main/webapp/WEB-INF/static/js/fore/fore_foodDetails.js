$(function () {
    var ul = $(".context_ul_goodsList").children("ul");

    $(".J_GoodsDetails").addClass("tab-selected");
    $(".context_img_li").eq(0).addClass("context_img_li_hover");

    //搜索框验证
    $('form').submit(function () {
        if ($(this).find("input[name='product_name']").val() === "") {
            alert("请输入关键字！");
            return false;
        }
    });
    //移入预览图片列表时
    $(".context_img_li").mouseenter(function () {
        var img = $(this).children("img");
        $(".context_img_main").attr("src", img.attr("src"));
        $(".context_img_ks").children("img").attr("src", img.attr("src"));
        $(".context_img_li").removeClass("context_img_li_hover");
        $(this).addClass("context_img_li_hover");
    });
    //产品数量框验证
    $(".amount_value_up").click(function () {
        var number = parseInt($(".context_buymember").val());
        number++;
        $(".context_buymember").val(number);
    });
    $(".amount_value-down").click(function () {
        var number = parseInt($(".context_buymember").val());
        if (number > 1) {
            number--;
            $(".context_buymember").val(number);
        }
    });
    $(".context_buymember").on("input", function () {
        if ($(this).val() === "") {
            $(this).val(1);
        }
        if (parseInt($("#stock").val()) < parseInt($(this).val())) {
            $(".context_buyNow").addClass("context_notBuy").attr("disabled", "disabled");
            $(".context_addBuyCar").addClass("context_notCar").attr("disabled", "disabled");
        } else {
            $(".context_buyNow").removeClass("context_notBuy").attr("disabled", null);
            $(".context_addBuyCar").removeClass("context_notCar").attr("disabled", null);
        }
    });
    //点击猜你喜欢翻页按钮时
    $(".ul_trigger_up").click(function () {
        var ulTop = parseInt(ul.css("top"));
        var fTop = ulTop + 480;
        if (fTop > 0) {
            ul.animate({
                top: ulTop + 40
            }, 100, function () {
                ul.animate({
                    top: 0
                }, 100);
            });
        } else {
            ul.animate({
                top: fTop
            }, 200);
        }
    });
    $(".ul_trigger_down").click(function () {
        var ulTop = parseInt(ul.css("top"));
        var fTop = ulTop - 480;
        if (ul.height() < 2880) {
            getGuessLoveProducts();
        }
        if (fTop < -2400) {
            ul.animate({
                top: ulTop - 40
            }, 100, function () {
                ul.animate({
                    top: -2400
                }, 100);
            });
        } else {
            ul.animate({
                top: fTop
            }, 200);
        }
    });
});

function getDetailsPage(obj, className) {
    $(".J_TabBarBox").find("li").removeClass("tab-selected");
    $(obj).parent("li").addClass("tab-selected");
    $(".J_choose").children("div").hide();
    $("." + className).show();
}

function getGuessLoveProducts() {
    $.ajax({
        type: "GET",
        url: "/guess/" + $("#tid").val(),
        data: {"guessNumber": $("#guessNumber").val()},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                $("#guessNumber").val(data.guessNumber);
                for (var i = 0; i < data.loveProductList.length; i++) {
                    var src = data.loveProductList[i].singleProductImageList[0].productImage_src;
                    var product_id = data.loveProductList[i].product_id;
                    var product_sale_price = data.loveProductList[i].product_sale_price;
                    $(".context_ul_goodsList").children("ul").append("<li class='context_ul_main'><div class='context_ul_img'>" +
                        "<a href='/product/" + product_id + "'><img src='/static/images/item/productSinglePicture/" + src + "'/></a><p>¥" + product_sale_price + ".00</p></div></li>"
                    );
                }
            }
        }
    });
}

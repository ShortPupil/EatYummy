<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/header.jsp" %>

<head>
    <script src="${pageContext.request.contextPath}/static/js/sell/sell_home.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/sell/sell_home.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/buttons.css" rel="stylesheet">
    <title>商家主页</title>
  </head>
  <nav>
    <%@ include file="include/navigator.jsp" %>
    </nav>
  <body>

    <div class="banner_do">
        <div class="search bar1" id="smallLay" name="smallLay" style="display:none">
            <form action="" method="post" class="elegant-aero" enctype="multipart/form-data">
                <h1>新增食品信息
                <span>填写下列表格</span>
                </h1>
                <label>
                    <span>名称</span>
                    <input id="food_name" type="text" name="food_name" placeholder="设置食品名称" />
                </label>
                <label>
                    <span>价格</span>
                    ￥<input id="food_price" type="text" name="food_price" placeholder="设置食品价格" />
                </label>
                <label>
                    <span>库存</span>
                    <input id="food_amount" type="text" name="food_amount" placeholder="设置食品库存"/>
                </label>
                <label>
                    <span>开始销售</span>
                    <input id="food_start_time" type="datetime-local" name="food_start_time"/>
                </label>
                <label>
                    <span>结束销售</span>
                    <input id="food_end_time" type="datetime-local" name="food_end_time"/>
                </label>
                <label>
                    <span>描述</span>
                    <textarea id="food_discription" type="text" name="food_discription" placeholder="设置对食品的描述"></textarea>
                </label>
                <label>
                    <span>图片上传</span>
                    <!--input type="file" id="file" name="file"/>
                    <button type="button" id="picture_sub" onclick="pictureSub(file)">提交</button>
                    <input type="reset"-->
                    <input id="picture" type="file" name="picture">
                </label>
                <label>
                    <span></span>
                    <button type="button" id="add_food" onclick="addFood()" class="button button-glow button-border button-rounded button-primary">新增</button>
                </label>
            </form>
        </div>

        <div class="search bar1" id="addS" name="addS" style="display:none">
            <form action="" method="post" class="elegant-aero" enctype="multipart/form-data">
                <h1>新增促销策略
                <span>填写下列表格</span>
                </h1>
                <label>
                    <span>满</span>
                    ￥<input id="strategy_full" type="text" name="strategy_full" />
                </label>
                <label>
                    <span>减</span>
                    ￥<input id="strategy_discount" type="text" name="strategy_discount" />
                </label>
                <label>
                    <span></span>
                    <button type="button" id="add_strategy" onclick="addStrategy()" class="button button-glow button-border button-rounded button-primary">新增</button>
                </label>
            </form>
        </div>


        <div class="banner_goods">
            <div class="banner_goods_type">
                <h3>餐厅名：<h2>${restaurant.name}</h2></h3>
                <p>餐厅详细地址：${restaurant.localAddress}</p>
                <br><br><br>

                <div class="add_food">
                    <a class="vintage5">新增餐品</a>
                    <button class="button button-primary button-circle button-giant" onclick="showid('smallLay')"><i class="fa fa-plus">+</i></button>
                    <br>
                </div>
                <div class="banner_goods_items">
                    <!--在foreach 循环中原本的元素是会被替换并重新多次创建的-->
                    <c:forEach items="${requestScope.foodList}" var="food">
                        <div class="banner_goods_item">
                            <img class="img" src="${food.picture}">
                            <a name="food_id" id="food_id">${food.id}</a>
                            <br>名称<input name="food_name_${food.id}" id="food_name_${food.id}" placeholder="${food.name}"  maxlength="10"> </input></br>
                            <br>价格<input name="food_price_${food.id}" id="food_price_${food.id}" placeholder="${food.price}"  maxlength="10"> </input></br>
                            <br>库存<input name="food_amount_${food.id}" id="food_amount_${food.id}" placeholder="${food.amount}"  maxlength="10"> </input></br>
                            <br>
                            <button type="button" id="update_food" onclick="updateFood(${food.id});" class="button button-glow button-border button-rounded button-primary">修改</button>
                            <button type="button" id="delete_food" onclick="deleteFood(${food.id});" class="button button-glow button-border button-rounded button-primary">删除</button>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="banner_goods">
            <div class="banner_goods_type">

                <div class="add_strategy">
                    <a class="vintage5">新增策略</a>
                    <button class="button button-primary button-circle button-giant" onclick="showid('addS')"><i class="fa fa-plus">+</i></button>
                    <br>
                </div>

                <div class="banner_goods_items">
                    <c:forEach items="${requestScope.strategy1List}" var="strategy">
                        <div class="banner_goods_item">
                            <br>满￥<input name="stategy_full_${strategy.id}" id="strategy_full_${strategy.id}" placeholder="${strategy.full}"  maxlength="10"> </input></br>
                            <br>减￥<input name="stategy_discount_${strategy.id}" id="strategy_discount_${strategy.id}" placeholder="${strategy.discount}"  maxlength="10"> </input></br>
                            <br>
                            <button type="button" id="update_strategy" onclick="updateStrategy(${strategy.id});" class="button button-glow button-border button-rounded button-primary">修改</button>
                            <button type="button" id="delete_strategy" onclick="deleteStrategy(${strategy.id});" class="button button-glow button-border button-rounded button-primary">删除</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="endDiv"></div>
    </div>
    <%@ include file="include/footer.jsp" %>
  </body>

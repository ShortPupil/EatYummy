<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta name="baidu-site-verification" content="zyu2elKF1h">
  <meta name="robots" content="noodp">

  <script src="${pageContext.request.contextPath}/static/js/sell/sell_register.js"></script>
  <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=310e4085efc26b34dca89cf0a1131192"></script>
  <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

  <link href="${pageContext.request.contextPath}/static/css/sell/sell_register.css" rel="stylesheet">


  <style type="text/css">
    /*删除高德地图的logo*/
    .amap-logo {
      right: 0 !important;
      left: auto !important;
      display: none;
    }
    amap-copyright {
      right: 70px !important;
      left: auto !important;

    }
    </style>


  <title>Yummy! - 商家入驻</title>

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
                <span class="span_tmallRegister">商家入驻</span></a>
        </div>
    </div>
</nav>
<div class="content" style="align-content: center">
    <div class="steps">
        <div class="steps_main">
            <span class="steps_tsl">填写店家信息</span>
        </div>
    </div>
    <div class="form-list">
        <div class="form-item">
            <label class="form-label form-label-b tsls">账户</label>
            <label class="form-label tsl">新建Yummy!唯一指定商家账户</label>
        </div>
        <div class="form-item">
            <button type="button" id="create_account">一键创建</button>
            <div id="account">
                <c:if test="${sessionScope.account != null}">
                    <br>
                    <p>您的账户id为 ${sessionScope.account.id}   您的账户余额为 ${sessionScope.account.balance}</p>
                </c:if>
            </div>
        </div>

        <div class="form-item">
            <label class="form-label form-label-b tsls">设置餐厅信息</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">餐厅名称：</label>
            <input name="restaurant_name" id="restaurant_name" class="form-text err-input" placeholder="请输入餐厅名称" maxlength="30">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">餐厅类型：</label>
            <input name="restaurant_type" id="restaurant_type" class="form-text err-input" placeholder="请输入餐厅名称" maxlength="30">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">餐厅分类：</label>
            <select id="restaurant_category" name="restaurant_category">
              <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                <option value=${category.id}>${category.name}</option>
              </c:forEach>
            </select>
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label form-label-b tsls">设置登录密码</label>
            <label class="form-label tsl">登录时验证，保护账号信息</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">登录密码：</label>
            <input name="restaurant_password" type="password" id="restaurant_password" class="form-text err-input"
                   placeholder="请设置登录密码" maxlength="30">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">确认密码：</label>
            <input name="restaurant_password_one" type="password" id="restaurant_password_one" class="form-text err-input"
                   placeholder="请再次输入你的密码" maxlength="30">
            <span class="form_span"></span>
        </div>

        <div class="form-item">
            <label class="form-label form-label-b tsls">设置地址</label>
            <label class="form-label tsl">店家地址</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">本地地址：</label>
            <input name="restaurant_address" id="restaurant_address" class="form-text err-input"
                   placeholder="输入您的本地地址（包括街道门牌等）"  maxlength="30">
            <span id="span_email"></span>
        </div>

        <div class="form-item">
            <label class="form-label tsl">店铺位置定位：</label>
            <div id="tip"></div>
            <div id='restaurant_coordinate' style="height: 500px;width: 500px"></div>

            <script type="text/javascript">
                var map, geolocation;
                //加载地图，调用浏览器定位服务
                map = new AMap.Map('restaurant_coordinate', {
                  resizeEnable: true
                });
                map.plugin('AMap.Geolocation', function () {
                  geolocation = new AMap.Geolocation({
                    enableHighAccuracy: true,//是否使用高精度定位，默认:true
                    timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                    buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                    zoomToAccuracy: false,      //定位区域
                    buttonPosition: 'RB'
                  });
                  map.addControl(geolocation);
                  geolocation.getCurrentPosition();
                  AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
                  AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
                });

                //解析定位结果
                function onComplete(data) {
                  var str = [''];
                  str.push('经纬度：')
                  str.push(data.position.getLng() + ' '+ data.position.getLat());
                  str.push();
                  str.push('');
                  document.getElementById('tip').innerHTML = str;
                }
                //解析定位错误信息
                function onError(data) {
                  document.getElementById('tip').innerHTML = '定位失败';
                }
            </script>
            <form>
              <script type="text/javascript">
                  var map = new AMap.Map('restaurant_coordinate',{
                    resizeEnable: true,
                    zoom: 10,
                    center: [116.480983, 40.0958]
                  });

                  map.plugin('AMap.Geolocation', function () {
                    geolocation = new AMap.Geolocation({
                      enableHighAccuracy: true,//是否使用高精度定位，默认:true
                      timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                      maximumAge: 0,           //定位结果缓存0毫秒，默认：0
                      convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
                      showButton: true,        //显示定位按钮，默认：true
                      buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
                      buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                      showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                      showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
                      panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
                      zoomToAccuracy:true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                    });

                    function onComplete(obj){
                      console.log(obj.message);
                    }

                    function  onError(obj){
                      console.log(obj.message);
                    }
                    map.addControl(geolocation);
                    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
                    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
                  });
                </script>
              </form>
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

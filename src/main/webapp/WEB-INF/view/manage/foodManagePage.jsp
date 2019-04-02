<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <style rel="stylesheet">
        #text_cut{
            position: relative;
            right: 10px;
            color: #ccc;
        }
        #lbl_product_isEnabled_special{
            margin-right: 20px;
        }
    </style>
</head>
<body>
<div class="frm_div text_info">
    <div class="frm_group">
        <label class="frm_label" id="lbl_product_isEnabled" for="checkbox_product_isEnabled_true">食品状态</label>
        <input id="checkbox_product_isEnabled_true" name="checkbox_product_isEnabled" type="checkbox" value="0" checked>
        <label class="frm_label" id="lbl_product_isEnabled_true" for="checkbox_product_isEnabled_true">销售中</label>
        <input id="checkbox_product_isEnabled_false" name="checkbox_product_isEnabled" type="checkbox" value="1" checked>
        <label class="frm_label" id="lbl_product_isEnabled_false" for="checkbox_product_isEnabled_false">停售中</label>
        <input id="checkbox_product_isEnabled_special" name="checkbox_product_isEnabled" type="checkbox" value="2" checked>
        <label class="frm_label" id="lbl_product_isEnabled_special" for="checkbox_product_isEnabled_special">促销中</label>
    </div>
    <div class="frm_group_last">
        <input class="frm_btn frm_refresh" id="btn_product_refresh" type="button" value="刷新产品列表"/>
        <span class="frm_error_msg" id="text_tools_msg"></span>
    </div>
</div>
<div class="data_count_div text_info">
    <svg class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2522" width="16" height="16">
            <path d="M401.976676 735.74897c-88.721671 0-172.124196-34.635845-234.843656-97.526197-62.724577-62.86784-97.271394-146.453537-97.271394-235.358379s34.546817-172.490539 97.276511-235.361449c62.715367-62.887282 146.117892-97.522104 234.838539-97.522104 88.719624 0 172.135452 34.633798 234.881518 97.522104 62.704111 62.875003 97.235578 146.4607 97.235578 235.361449 0 88.901773-34.530444 172.487469-97.231485 235.358379C574.112128 701.116195 490.6963 735.74897 401.976676 735.74897zM401.976676 121.204479c-75.012438 0-145.533584 29.290093-198.572568 82.474386-109.585861 109.834524-109.585861 288.539602-0.004093 398.36901 53.043077 53.188386 123.564223 82.47848 198.577684 82.47848 75.015507 0 145.553027-29.291117 198.620663-82.47848C710.126918 492.220514 710.126918 313.511343 600.593246 203.678866 547.530726 150.496619 476.992183 121.204479 401.976676 121.204479z" p-id="2523" fill="#FF7874">
            </path>
            <path d="M932.538427 958.228017c-6.565533 0-13.129019-2.508123-18.132986-7.52437L606.670661 642.206504c-9.989515-10.014074-9.969049-26.231431 0.045025-36.220946s26.230408-9.969049 36.220946 0.045025l307.73478 308.497143c9.989515 10.014074 9.969049 26.231431-0.045025 36.220946C945.627537 955.735244 939.081447 958.228017 932.538427 958.228017z" p-id="2524" fill="#FF7874">
            </path>
        </svg>
    <span class="data_count_title">查看合计</span>
    <span>产品总数:</span>
    <span class="data_count_value" id="product_count_data">${requestScope.productCount}</span>
    <span class="data_count_unit">件</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_product_list">
        <thead class="text_info">
        <tr>
            <th><input type="checkbox" class="cbx_select" id="cbx_select_all"><label for="cbx_select_all"></label></th>
            <th class="data_info" data-sort="asc" data-name="product_name">
                <span>产品名称</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="product_price">
                <span>原价</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="product_price">
                <span>数量</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="product_create_date">
                <span>上架时间</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="product_create_date">
                <span>下架时间</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="product_isEnabled">
                <span>上架状态</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th>操作</th>
            <th hidden>产品ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.foodList}" var="food">
            <tr>
                <td><input type="checkbox" class="cbx_select" id="cbx_product_select_${food.id}"><label for="cbx_product_select_${food.id}"></label></td>
                <td title="${food.name}">${food.name}</td>
                <td title="${food.price}">${food.price}</td>
                <td title="${food.amount}">${food.amount}</td>
                <td title="${food.starttime}">${food.starttime}</td>
                <td title="${food.endtime}">${food.endtime}</td>
                <td>
                    <c:choose>
                        <c:when test="${food.type==1}"><span class="td_success" title="产品正常销售中">销售中</span></c:when>
                        <c:when test="${food.type==2}"><span class="td_warn" title="产品显示在主页促销中">促销中</span></c:when>
                        <c:otherwise><span class="td_error" title="产品缺货或违规停售中">停售中</span></c:otherwise>
                    </c:choose>
                </td>
                <td hidden><span class="id">${food.id}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="loader"></div>
</div>
</body>
</html>

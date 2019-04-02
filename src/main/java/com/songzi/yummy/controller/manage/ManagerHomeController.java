package com.songzi.yummy.controller.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Manager;
import com.songzi.yummy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ManagerHomeController extends BaseController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private FoodorderService foodorderService;
    @Autowired
    private FoodorderitemService foodorderitemService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FoodService foodService;

    //转到后台管理-主页
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("检查管理员权限");

        //session.setAttribute("managerId", (long)1);
        if(session.getAttribute("managerId") == null ||session.getAttribute("managerId").equals(null)){
            logger.info("错误");
            return "redirect:/manage/login";
        }
        Long managerId = (Long) checkManage(session);

        if (managerId != null) {
            logger.info("获取管理员信息");
            Manager manager =managerService.get(managerId);
            map.put("manager", manager);
        }
        logger.info("获取统计信息");
        long restaurantTotal = restaurantService.getTotal();
        long memberTotal = memberService.getTotal();
        long orderTotal = foodorderService.getTotal();
        long foodTotal = foodService.getTotal();
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null, null));
        map.put("restaurantTotal", restaurantTotal);
        map.put("foodTotal", foodTotal);
        map.put("memberTotal", memberTotal);
        map.put("orderTotal", orderTotal);

        logger.info("转到后台管理-主页");
        return "manage/homePage";
    }

    //转到后台管理-主页-ajax
    @RequestMapping(value = "/manage/home", method = RequestMethod.GET)
    public String goToPageByAjax(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if (managerId == null) {
            return "/manage/include/loginMessage";
        }

        logger.info("获取管理员信息");
        Manager manager = managerService.get((Long)managerId);
        map.put("manager", manager);
        logger.info("获取统计信息");
        long restaurantTotal = restaurantService.getTotal();
        long memberTotal = memberService.getTotal();
        long orderTotal = foodorderService.getTotal();
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null, null));
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null, null));
        map.put("productTotal", restaurantTotal);
        map.put("userTotal", memberTotal);
        map.put("orderTotal", orderTotal);
        logger.info("转到后台管理-主页-ajax方式");
        return "/manage/homeManagePage";
    }

    //按日期查询图表数据-ajax
    @ResponseBody
    @RequestMapping(value = "manage/home/charts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getChartDataByDate(@RequestParam(required = false) String beginDate, @RequestParam(required = false) String endDate) throws ParseException {
        if (beginDate != null && endDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return getChartData(simpleDateFormat.parse(beginDate), simpleDateFormat.parse(endDate)).toJSONString();
        } else {
            return getChartData(null, null).toJSONString();
        }
    }

    //获取图表的JSON数据
    private JSONObject getChartData(Date beginDate,Date endDate) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        SimpleDateFormat timeSpecial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
        if (beginDate == null || endDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -20);
            beginDate = time.parse(time.format(cal.getTime()));
            cal = Calendar.getInstance();
            endDate = cal.getTime();
        } else {
            beginDate = time.parse(time.format(beginDate));
            endDate = timeSpecial.parse(time.format(endDate) + " 23:59:59");
        }
        String[] dateStr = new String[20];
        SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        logger.info("获取时间段数组");
        for (int i = 0; i < dateStr.length; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginDate);
            cal.add(Calendar.DATE, i);
            dateStr[i] = time2.format(cal.getTime());
        }
        logger.info("获取总交易额订单列表");
        List<Foodorder> foodorderList = foodorderService.getTotalByDate(beginDate, endDate);
        logger.info("根据订单状态分类");
        //总交易订单数组
        int[] orderTotalArray = new int[20];
        //未付款订单数组
        int[] orderUnpaidArray = new int[20];
        //未确认订单数组
        int[] orderUnconfirmedArray = new int[20];
        //交易成功数组
        int[] orderSuccessArray = new int[20];
        for (Foodorder foodorder : foodorderList) {
            int index = 0;
            for (int j = 0; j < dateStr.length; j++) {
                if (dateStr[j].equals(foodorder.getUpdatedAt().toString().split(" ")[0])) {
                    index = j;
                }
            }
            switch (foodorder.getStatus()) {
                case 2:
                    orderUnpaidArray[index] = foodorder.getFoodorderitemsById().size();
                    break;
                case 3:
                    orderUnconfirmedArray[index] = foodorder.getFoodorderitemsById().size();
                    break;
                case 4:
                    orderSuccessArray[index] = foodorder.getFoodorderitemsById().size();
                    break;
            }
        }
        logger.info("获取总交易订单数组");
        for (int i = 0; i < dateStr.length; i++) {
            orderTotalArray[i] = orderUnpaidArray[i]+ orderUnconfirmedArray[i] + orderSuccessArray[i];
        }
        logger.info("返回结果集map");
        jsonObject.put("orderTotalArray", JSONArray.parseArray(JSON.toJSONString(orderTotalArray)));
        jsonObject.put("orderUnpaidArray", JSONArray.parseArray(JSON.toJSONString(orderUnpaidArray)));
        jsonObject.put("orderUnconfirmedArray", JSONArray.parseArray(JSON.toJSONString(orderUnconfirmedArray)));
        jsonObject.put("orderSuccessArray", JSONArray.parseArray(JSON.toJSONString(orderSuccessArray)));
        jsonObject.put("dateStr",JSONArray.parseArray(JSON.toJSONString(dateStr)));
        return jsonObject;
    }
}

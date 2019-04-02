package com.songzi.yummy.controller.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.service.*;
import com.songzi.yummy.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Controller
public class ManagerOrderController extends BaseController {

    @Autowired
    private FoodorderService foodorderService;
    @Autowired
    private FoodorderitemService foodorderitemService;


    //转到后台管理-订单页-ajax
    @RequestMapping(value = "manage/order", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if(managerId == null){
            return "manage/include/loginMessage";
        }

        List<Foodorder> foodorderList = foodorderService.getAllOrders();
        map.put("foodorderList",foodorderList);
        logger.info("获取订单总数量");
        int foodorderCount = foodorderList.size();
        map.put("foodorderCount", foodorderCount);

        logger.info("转到后台管理-订单页-ajax方式");
        return "manage/orderManagePage";
    }

    //转到后台管理-订单详情页-ajax
    @RequestMapping(value = "manage/order/{oid}", method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable long oid/* 订单ID */) {
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if(managerId == null){
            return "manage/include/loginMessage";
        }

        logger.info("获取order_id为{}的订单信息",oid);
        Foodorder foodorder = foodorderService.get(oid);

        logger.info("获取订单详情-订单项信息");
        List<Foodorderitem> foodorderitemList = foodorderitemService.getOrderitemListByOrderId(oid);

        map.put("foodorder", foodorder);
        map.put("foodorderitemList", foodorderitemList);
        logger.info("转到后台管理-订单详情页-ajax方式");
        return "manage/include/orderDetails";
    }
}

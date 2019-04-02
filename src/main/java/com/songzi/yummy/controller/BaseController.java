package com.songzi.yummy.controller;

import com.songzi.yummy.service.ManagerService;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * 基控制器
 */
public class BaseController {
    //log4j2
    protected Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    MemberService memberService;
    @Autowired
    ManagerService managerService;
    @Autowired
    RestaurantService restaurantService;

    //检查管理员权限
    protected Object checkManage(HttpSession session){
        Object o = session.getAttribute("managerId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("权限验证成功，管理员ID：{}",o);

        return o;
    }

    //检查用户是否登录
    protected Object checkMember(HttpSession session){
        Object o = session.getAttribute("memberId");
        if(o==null){
            logger.info("用户未登录");
            return null;
        }
        logger.info("用户已登录，用户id：{}", o);

        return o;
    }

    //检查餐厅是否登录
    protected Object checkRestaurant(HttpSession session){
        Object o = session.getAttribute("restaurantId");
        if(o==null){
            logger.info("餐厅未登录");
            return null;
        }
        logger.info("餐厅已登录，餐厅id：{}", o);
        return o;
    }
}

package com.songzi.yummy.controller.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RestaurantLoginController extends BaseController {
    @Autowired
    private RestaurantService restaurantService;

    //转到前台-登录页
    @RequestMapping(value = "restaurant/login", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到前台登录页");
        return "restaurant/restaurantLoginPage";
    }

    //登陆验证-ajax
    @ResponseBody
    @RequestMapping(value = "restaurant/login/doLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String codenumber, @RequestParam String password) {
        logger.info("餐厅验证登录");

        Restaurant restaurant = restaurantService.login(codenumber, password);

        JSONObject jsonObject = new JSONObject();
        if (restaurant == null) {
            logger.info("登录验证失败");
            jsonObject.put("success", false);
        } else {
            logger.info("登录验证成功,商家ID传入会话");
            session.setAttribute("restaurantId", restaurant.getId());
            jsonObject.put("success", true);
        }
        return jsonObject.toJSONString();
    }

    //退出当前账号
    @RequestMapping(value = "restaurant/login/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        Object o = session.getAttribute("restaurantId");
        if (o != null) {
            session.removeAttribute("restaurantId");
            session.invalidate();
            logger.info("登录信息已清除，返回用户登录页");
        }
        return "redirect:/restaurant/login";
    }
}

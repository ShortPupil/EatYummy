package com.songzi.yummy.controller.restaurant;

import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Address;
import com.songzi.yummy.entity.Level;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.service.AddressService;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class RestaurantController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;

    //转到前台-用户详情页
    @RequestMapping(value = "/restaurant/restaurantDetails", method = RequestMethod.GET)
    public String goToUserDetail(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        Object restaurantId = checkRestaurant(session);
        if (restaurantId != null) {
            logger.info("获取用户信息");
            Restaurant restaurant = restaurantService.getById((long) restaurantId);
            map.put("restaurant", restaurant);
            return "restaurant/restaurantDetails";
        } else {
            return "redirect:/restaurant/login";
        }
    }

    //前台-用户详情更新
    @ResponseBody
    @RequestMapping(value = "/restaurant/doUpdateInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String userUpdate(HttpSession session,
                             @RequestParam(value = "restaurant_name") String restaurant_name  ,
                             @RequestParam(value = "restaurant_password") String restaurant_password ,
                             @RequestParam(value = "restaurant_local_address") String restaurant_local_address
    ) throws ParseException, UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object restaurantId = checkRestaurant(session);
        if (restaurantId != null) {
            logger.info("获取用户信息");
            Restaurant restaurant = restaurantService.getById((long) restaurantId);

            logger.info("创建用户对象");
            if ( restaurant_name.equals(""))
                restaurant.setName(restaurant_name);
            if (!restaurant_password.equals(""))
                restaurant.setPassword(restaurant_password);
            if (!restaurant_local_address.equals(""))
                restaurant.setLocalAddress(restaurant_local_address);
            logger.info("执行修改");
            if (restaurantService.update(restaurant)) {
                logger.info("修改成功!跳转到餐厅页面");
                return "redirect:/restaurant/restaurantDetails";
            }
        } else {
            return "redirect:/restaurant/login";
        }

        throw new RuntimeException();
    }
}

package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class BuyFoodDetailsController extends BaseController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RestaurantService restaurantService;

    //-产品详情页
    @RequestMapping(value = "/food/{food_id}", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @PathVariable("food_id") String food_id /*食物id*/) {
        logger.info("检查用户是否登录");
        Object memberId = checkMember(session);
        if (memberId != null) {
            logger.info("获取用户信息");
            Member member = memberService.get((Long)memberId);
            map.put("member", member);
        }
        logger.info("获取食品ID");
        long fid = Long.valueOf(food_id);
        logger.info("获取产品信息");
        Food food = foodService.get(fid);
        if (food == null || food.getEndtime().before(new Timestamp(System.currentTimeMillis()))) {
            return "redirect:/404";
        }

        //暂未实现
        logger.info("获取产品子信息-销量数和评论数信息");

        logger.info("获取同个餐厅的菜品");
        List<Food> restaurantFoodList = foodService.getFoodByRestaurant(food.getResId());
        restaurantFoodList.remove(food);

        Restaurant restaurant = restaurantService.getById(food.getResId());
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getCategoryForHomePage();

        map.put("restaurantFoodList", restaurantFoodList);
        map.put("restaurant", restaurant);
        map.put("categoryList", categoryList);
        map.put("food", food);
        logger.info("转到前台-产品详情页");
        return "buy/foodDetailsPage";
    }

    //按产品ID加载产品属性列表-ajax
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "property/{pid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String loadProductPropertyList(@PathVariable("pid") String pid/*产品ID*/) {
        logger.info("获取产品ID");
        Integer product_id = Integer.parseInt(pid);

        logger.info("获取产品详情-属性值信息");

        JSONObject jsonObject = new JSONObject();
        return jsonObject.toJSONString();
    }

    //加载猜你喜欢列表-ajax
    @ResponseBody
    @RequestMapping(value = "guess/{cid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String guessYouLike(@PathVariable("cid") Integer cid, @RequestParam Integer guessNumber) {
        logger.info("获取猜你喜欢列表");
        JSONObject jsonObject = new JSONObject();
        return jsonObject.toJSONString();
    }
}

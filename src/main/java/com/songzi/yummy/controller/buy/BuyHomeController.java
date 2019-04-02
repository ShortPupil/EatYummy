package com.songzi.yummy.controller.buy;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.bean.CategoryUseInHome;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.repository.CategoryRepository;
import com.songzi.yummy.service.CategoryService;
import com.songzi.yummy.service.FoodService;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.service.RestaurantService;
import com.songzi.yummy.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class BuyHomeController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryService categoryService;

    //转到前台天猫-主页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        Long memberId = (Long) checkMember(session);

        if (memberId != null) {
            logger.info("获取用户信息");
            Member member = memberService.get(memberId);
            map.put("member", member);
        }

        logger.info("获取产品分类列表");

        List<Category> categoryList = categoryService.getCategoryForHomePage();
        map.put("categoryList",categoryList);

        List<Restaurant> restaurantList = restaurantService.getRestaurantsByName(null);
        logger.info("获取每个餐厅下的产品列表");
        for(Restaurant restaurant : restaurantList){
            logger.info("获取餐厅为{}的产品集合，按产品ID倒序排序", restaurant.getId());
            Collection<Food> foods = foodService.getFoodByRestaurant(restaurant.getId());//.getList(new Product().setProduct_category(category), new Byte[]{0, 2}, new OrderUtil("product_id", true), new PageUtil(0, 8));
            if (foods != null) {
                for (Food food : foods) {
                    long food_id = food.getId();
                    logger.info("获取产品id为{}的产品预览图片信息", food_id);
                }
            }
            //restaurant.setFoodsById(foods);
        }

        ArrayList<Food> specialFoodList = new ArrayList<Food>();
        specialFoodList.add(foodService.get(1));
        specialFoodList.add(foodService.get(2));
        specialFoodList.add(foodService.get(3));
        specialFoodList.add(foodService.get(4));
        specialFoodList.add(foodService.get(5));
        map.put("specialFoodList",specialFoodList);
        map.put("restaurantList",restaurantList);
        map.put("categoryList", categoryList);
        logger.info("获取促销产品列表");
        //List<Product> specialProductList = productService.getList(null, new Byte[]{2}, null, new PageUtil(0, 6));
        //map.put("specialProductList", specialProductList);

        logger.info("转到前台主页");
        return "buy/homePage";
    }

    //转到前台天猫-错误页
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String goToErrorPage() {
        return "errorPage";
    }

    //获取主页分类下产品信息-ajax
    @ResponseBody
    @RequestMapping(value = "restaurant/nav/{category_id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductByNav(@PathVariable("category_id") Long category_id) {
        JSONObject object = new JSONObject();
        if (category_id == null) {
            object.put("success", false);
            return object.toJSONString();
        }
        logger.info("获取分类ID为{}的商店标题数据", category_id);
        Category category = categoryService.get(category_id);
        List<Restaurant> restaurantList = restaurantService.getRestaurantByCategory(category_id.longValue());
        List<List<Restaurant>> complexRestaurantList = new ArrayList<>(8);
        List<Restaurant> restaurants = new ArrayList<>(5);
        for (int i = 0; i < restaurantList.size(); i++) {
            //如果临时集合中产品数达到5个，加入到产品二维集合中，并重新实例化临时集合
            if (i % 5 == 0) {
                complexRestaurantList.add(restaurants);
                restaurants = new ArrayList<>(5);
            }
            restaurants.add(restaurantList.get(i));
        }
        complexRestaurantList.add(restaurants);
        CategoryUseInHome categoryUseInHome = new CategoryUseInHome(category, complexRestaurantList);

        object.put("success", true);
        object.put("categoryUseInHome", categoryUseInHome);
        return object.toJSONString();
    }
}

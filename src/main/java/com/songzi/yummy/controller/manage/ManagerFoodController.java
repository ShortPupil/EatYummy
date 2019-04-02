package com.songzi.yummy.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.songzi.yummy.controller.BaseController;
import com.songzi.yummy.entity.Category;
import com.songzi.yummy.entity.Food;
import com.songzi.yummy.service.CategoryService;
import com.songzi.yummy.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class ManagerFoodController extends BaseController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FoodService foodService;

    //转到后台管理-产品页-ajax
    @RequestMapping(value = "manage/food",method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查管理员权限");
        Object managerId = checkManage(session);
        if(managerId == null){
            return "manage/include/loginMessage";
        }

        logger.info("获取食品分类列表");
        List<Category> categoryList = categoryService.getCategoryForHomePage();
        map.put("categoryList", categoryList);
        logger.info("获取食品列表");
        List<Food> foodList = foodService.getAllFood();
        map.put("foodList", foodList);
        logger.info("获取食品总数量");
        long foodCount = foodService.getTotal();
        map.put("foodCount", foodCount);

        logger.info("转到后台管理-产品页-ajax方式");
        return "manage/foodManagePage";
    }

}

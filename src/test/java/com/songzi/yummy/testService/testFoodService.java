package com.songzi.yummy.testService;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.entity.Food;
import com.songzi.yummy.service.FoodService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyApplication.class)
@WebAppConfiguration
public class testFoodService {

    @Autowired
    private FoodService foodService;

    @Test
    public void test_1(){
        Food food = foodService.get(1);
        List<Food> restaurantFoodList = foodService.getFoodByRestaurant(food.getResId());
        Assert.assertEquals(4, restaurantFoodList.size());
    }

    @Test
    public void test_2(){
        List<Food> foodList = foodService.getFoodByName("米");
        Assert.assertEquals(1, foodList.size());
    }

    @Test
    public void test_3(){
        List<Food> foods = foodService.getFoodByPrice(2, 100);
        Assert.assertEquals(14, foods.size());
    }

    @Test
    public void test_4() {
        List<Food> foodList = foodService.getFoodByRestaurant((long)1);
        Assert.assertEquals(4,foodList.size());
        Assert.assertEquals("ok", foodList.get(0).getName());
    }
}

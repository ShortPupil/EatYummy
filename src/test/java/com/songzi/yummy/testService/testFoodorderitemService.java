package com.songzi.yummy.testService;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.service.FoodorderitemService;
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
public class testFoodorderitemService {

    @Autowired
    private FoodorderitemService foodorderitemService;

    @Test
    public void test_1(){
        System.out.println(foodorderitemService.getOrderitemListByOrderId(1).size());
    }

    @Test
    public void test_2(){
        Assert.assertEquals(foodorderitemService.getOrderitemListByMemId(1).size(), 2);
    }

    @Test
    public void test_3(){
        Assert.assertEquals(foodorderitemService.get(1).getNumber(), 1, 0);
    }

    @Test
    public void test_4(){
        List<Foodorderitem> item = foodorderitemService.getOrderitemListByOrderId((long)2);
        Assert.assertEquals(1, item.size());
    }
}

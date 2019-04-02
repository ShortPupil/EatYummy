package com.songzi.yummy.testDao;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.repository.FoodorderitemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyApplication.class)
@WebAppConfiguration
public class testFoodorderitemDAO {
    @Autowired
    FoodorderitemRepository foodorderitemRepository;

    @Test
    public void test_1(){
        foodorderitemRepository.findByFoodorderId(1);
    }

    @Test
    public void test3(){
        Assert.assertEquals(foodorderitemRepository.findByFoodorderId((long)2).size(), 1);
    }
}

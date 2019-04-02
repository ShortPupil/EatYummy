package com.songzi.yummy.testDao;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.repository.FoodorderitemRepository;
import com.songzi.yummy.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyApplication.class)
@WebAppConfiguration
public class testRestaurantDao {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void test_1(){
        System.out.println(restaurantRepository.findRestaurantsByCategory(1).size());
    }
}

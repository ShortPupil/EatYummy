package com.songzi.yummy.testService;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.service.RestaurantService;
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
public class testRestaurantService {
    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void test_1(){
        Restaurant restaurant = restaurantService.login("cdNjKTw", "1234");
        Assert.assertEquals(restaurant.getPassword(), "1234");
    }
}

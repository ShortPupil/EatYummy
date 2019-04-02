package com.songzi.yummy.testDao;


import com.songzi.yummy.entity.Food;
import com.songzi.yummy.entity.Restaurant;
import com.songzi.yummy.repository.FoodRepository;
import com.songzi.yummy.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testFoodDao {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    //@Test
    public void testTimeSearch() throws Exception {
        ZonedDateTime t = ZonedDateTime.now();
        ZonedDateTime t2 = ZonedDateTime.now();
        //long fs = foodRepository.countFoodByTime(t,t2);
        //System.out.println(fs);
    }

    @Test
    public void getFoodByRes() throws Exception{
        Restaurant res = restaurantRepository.getOne((long)1);
        System.out.println(res.getFoodsById().size());
    }

    @Test
    public void testgetFoodByName() throws Exception{
        List<Food> food = foodRepository.findByNameContaining("饭");
        System.out.println(food.size());
    }

}

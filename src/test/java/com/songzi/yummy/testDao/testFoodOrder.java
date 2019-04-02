package com.songzi.yummy.testDao;

import com.songzi.yummy.repository.FoodorderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testFoodOrder {
    @Autowired
    FoodorderRepository foodorderRepository;

    @Test
    public void test2(){
        System.out.println(
                foodorderRepository.getOne((long)1).getAddressByAddressId().getCoordinate());
    }

    @Test
    public void test1(){
        //System.out.println( foodorderRepository.getOne((long)1).getFoodorderitemsById().size());
    }
}

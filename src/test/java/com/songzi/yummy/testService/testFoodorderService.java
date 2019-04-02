package com.songzi.yummy.testService;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.entity.Foodorder;
import com.songzi.yummy.entity.Foodorderitem;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.FoodorderService;
import com.songzi.yummy.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyApplication.class)
@WebAppConfiguration
public class testFoodorderService {

    @Autowired
    MemberService memberService;
    @Autowired
    FoodorderService foodorderService;

    @Test
    public void test_1(){
        Member member = memberService.get(1);
        List<Foodorder> foodorders = foodorderService.getOrderByMember(member);
        System.out.println(foodorders.get(0).getPhonenumber());
        //Assert.assertEquals(foodorders.get(0).getPhonenumber(), "1");
    }

    @Test
    public void test_2(){
        Foodorder foodorder = foodorderService.get(1);
        System.out.println(foodorder.getMemId() + " " + foodorder.getMemberByMemId().getId());
        Assert.assertEquals(foodorder.getMemId(), 1, 0);
    }

    @Test
    public void test_3(){
        List<Foodorder> foodorderList = foodorderService.getOrderByStatus((byte)1);
        Assert.assertEquals(foodorderList.size(), 1);
    }

    @Test
    public void test_4(){
        List<Foodorder> foodorderList = foodorderService.getOrderByResId((long)1);
        Assert.assertEquals(2, foodorderList.size());
    }

}

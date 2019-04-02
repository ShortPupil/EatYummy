package com.songzi.yummy.testService;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.service.MemberService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = YummyApplication.class)
@WebAppConfiguration
public class testMemberService {

    @Autowired
    MemberService m;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @Test
    @Transactional
    public void test1(){
        Member r = m.login("1", "1234");
        System.out.println(r.getEmail());
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}

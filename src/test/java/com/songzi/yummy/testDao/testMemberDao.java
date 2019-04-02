package com.songzi.yummy.testDao;

import com.songzi.yummy.YummyApplication;
import com.songzi.yummy.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMemberDao {
    @Autowired
    private MemberRepository m;

    @Test
    public void listAll() throws Exception {
        if(m.count() == 0){
            System.out.println("ok");
        }
    }
}

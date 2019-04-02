package com.songzi.yummy.testController.buy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songzi.yummy.entity.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class testBuyLoginController {


    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //@Test
    public void addGirl() throws Exception {
        //ObjectMapper 是一个可以重复使用的对象
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"cupSize\":\"B\", \"age\":19, \"money\":22.22}";
        //将JSON字符串值转换成 Girl对象里的属性值
        Member girl = mapper.readValue(jsonString, Member.class);
        mvc.perform(MockMvcRequestBuilders.post("/girlsss")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                /* 使用writeValueAsString() 方法来获取对象的JSON字符串表示 */
                .content(mapper.writeValueAsString(girl)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.cupSize").value("B"))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.age").value(19))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.money").value(22.22));
    }
}

package com.eric.dairy.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DairyControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test

    public void getDays() throws Exception {
        String ans = "[[\"2018-03-21\",23010],[\"1970-01-18\",120]]";

        mvc.perform(MockMvcRequestBuilders.get("/dairy/days"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans));
    }

    @Test
    public void getDairy() throws Exception {
        String ans1 = "[{\"id\":1,\"date\":\"1970-01-18\",\"description\":\"test\",\"calories\":120,\"marked\":false},{\"id\":2,\"date\":\"2018-03-21\",\"description\":\"test\",\"calories\":120,\"marked\":true},{\"id\":3,\"date\":\"2018-03-21\",\"description\":\"test1\",\"calories\":220,\"marked\":true},{\"id\":4,\"date\":\"2018-03-21\",\"description\":\"\",\"calories\":120,\"marked\":true},{\"id\":5,\"date\":\"2018-03-21\",\"description\":\"morning\",\"calories\":420,\"marked\":true},{\"id\":6,\"date\":\"2018-03-21\",\"description\":\"eat alot\",\"calories\":22130,\"marked\":true}]";

        String ans2 = "[{\"id\":1,\"date\":\"1970-01-18\",\"description\":\"test\",\"calories\":120,\"marked\":false}]";

        String ans3 = "[{\"id\":5,\"date\":\"2018-03-21\",\"description\":\"morning\",\"calories\":420,\"marked\":true}]";

        mvc.perform(MockMvcRequestBuilders.get("/dairy/")
                .param("id", "").param("description", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans1));

        mvc.perform(MockMvcRequestBuilders.get("/dairy/")
                .param("id", "1").param("description", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans2));

        mvc.perform(MockMvcRequestBuilders.get("/dairy/")
                .param("id", "").param("description", "morning"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans3));
    }

    @Test
    public void postDairy() throws Exception{
        String ans = "1";

        String content = "{\n" +
                "\t\"date\": \"1521602049584\",\n" +
                "\t\"description\": \"eat alot\", \n" +
                "\t\"calories\": 22130\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("/dairy/").content(content)
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans));

    }

    @Test
    public void deleteDairy() throws Exception {
        String ans = "1";

        mvc.perform(MockMvcRequestBuilders.delete("/dairy/1")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ans));
    }
}
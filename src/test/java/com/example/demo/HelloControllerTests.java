package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testRootEndpoint() throws Exception {
        mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Spring!"));
    }

    @Test
    public void testMathPiEndpoint() throws Exception {
        mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Math.PI)));
    }

    @Test
    public void testCalculateEndpoint() throws Exception {
        mvc.perform(get("/math/calculate?x=3&y=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void testSumEndpoint() throws Exception {
        mvc.perform(get("/math/sum?n=3&n=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
}
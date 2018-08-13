package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordsController.class)
public class WordsControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void countTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Home, Sweet Home");
        mvc.perform(request)
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.translationProperties", Matchers.hasEntry("Home", new Integer(2))))
//                .andExpect(jsonPath("$.translationProperties", Matchers.hasEntry("Sweet", new Integer(1))));
    }
}
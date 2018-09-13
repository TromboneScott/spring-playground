package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordsController.class)
public class WordsControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WordCounter wordCounter;

    @Before
    public void setup() {
        when(wordCounter.count("MOCK")).thenReturn(null);
    }

    @Test
    public void countTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Home, Sweet Home");
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void mockCountTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("MOCK");
        mvc.perform(request)
                .andExpect(status().isOk());
    }
}
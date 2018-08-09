package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        String title = "TEST CREATE";

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"" + title + "\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(title)));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        String title = "TEST UPDATE";

        Lesson lesson = new Lesson();
        lesson.setTitle("ORIGINAL");
        repository.save(lesson);

        MockHttpServletRequestBuilder request = patch("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"" + title + "\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(title)));
    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception {
        String title = "TEST LIST";

        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo(title)));
    }
}
package com.example.demo;

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

import java.text.SimpleDateFormat;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        Long id = repository.save(lesson).getId();

        MockHttpServletRequestBuilder request = patch("/lessons/" + id)
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

    @Test
    @Transactional
    @Rollback
    public void testFind() throws Exception {
        String title = "TEST FIND";

        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons/find/" + title)
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(title)));
    }

    @Test
    @Transactional
    @Rollback
    public void testBetween() throws Exception {
        String title = "TEST BETWEEN";

        Lesson lesson1 = new Lesson();
        lesson1.setTitle(title + ": IS NOT BETWEEN");
        lesson1.setDeliveredOn(new SimpleDateFormat("yyyy-MM-dd").parse("2017-07-04"));
        repository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle(title);
        lesson2.setDeliveredOn(new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-04"));
        repository.save(lesson2);

        MockHttpServletRequestBuilder request = get("/lessons/between?date1=2018-01-01&date2=2018-12-31")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo(title)));
    }
}
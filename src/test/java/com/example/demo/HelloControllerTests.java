package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.calculate("add", 3, 4)));
    }

    @Test
    public void testSumEndpoint() throws Exception {
        mvc.perform(get("/math/sum?n=3&n=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.sum(Arrays.asList("3", "4"))));
    }

    @Test
    public void testVolumeEndpoint() throws Exception {
        mvc.perform(get("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.volume(3, 4, 5)));
    }

    @Test
    public void testAreaEndpointCircle() throws Exception {
        AreaParams params = new AreaParams("circle", 4, 0, 0);
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", params.getType())
                .param("radius", String.valueOf(params.getRadius()));
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.area(params)));
    }

    @Test
    public void testAreaEndpointRectangle() throws Exception {
        AreaParams params = new AreaParams("rectangle", 0, 4, 7);
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", params.getType())
                .param("width", String.valueOf(params.getWidth()))
                .param("height", String.valueOf(params.getHeight()));
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.area(params)));
    }

    @Test
    public void testAreaEndpointInvalid() throws Exception {
        AreaParams params = new AreaParams("circle", 0, 0, 0);
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", params.getType());
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(MathService.area(params)));
    }
}
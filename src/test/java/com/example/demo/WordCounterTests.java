package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class WordCounterTests {
    @Test
    public void countTest() {
        Map<String, Integer> counts = new WordCounter().count("Home, Sweet Home");
        Assert.assertEquals(2, counts.size());
        Assert.assertEquals(2, counts.get("Home").intValue());
        Assert.assertEquals(1, counts.get("Sweet").intValue());
        Assert.assertEquals(null, counts.get("Work"));
    }
}
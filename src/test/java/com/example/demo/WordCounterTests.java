package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "word-count.caseSensitive=false",
        "word-count.words.skip[0]=the",
        "word-count.words.skip[1]=an",
        "word-count.words.skip[2]=a"
})
public class WordCounterTests {
    @Autowired
    private WordCounterConfig wordCounterConfig;

    @Test
    public void countTest() {
        Map<String, Integer> counts = new WordCounter(wordCounterConfig).count("Home, Sweet Home");
        Assert.assertEquals(2, counts.size());
        Assert.assertEquals(2, counts.get("Home").intValue());
        Assert.assertEquals(1, counts.get("Sweet").intValue());
        Assert.assertEquals(null, counts.get("Work"));
    }

    @Test
    public void countSkipTest() {
        Map<String, Integer> counts = new WordCounter(wordCounterConfig).count("The man and a dog and an apple");
        Assert.assertEquals(4, counts.size());
        Assert.assertEquals(1, counts.get("man").intValue());
        Assert.assertEquals(2, counts.get("and").intValue());
        Assert.assertEquals(1, counts.get("dog").intValue());
        Assert.assertEquals(1, counts.get("apple").intValue());
        Assert.assertEquals(null, counts.get("The"));
        Assert.assertEquals(null, counts.get("an"));
        Assert.assertEquals(null, counts.get("a"));
    }
}
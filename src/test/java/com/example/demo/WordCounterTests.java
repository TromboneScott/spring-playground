package com.example.demo;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class WordCounterTests {
    @Test
    public void countTest() {
        WordCounterConfig wordCounterConfig = new WordCounterConfig();
        wordCounterConfig.setCaseSensitive(true);
        WordCounterConfig.Words words = new WordCounterConfig.Words();
        words.setSkip(Lists.emptyList());
        wordCounterConfig.setWords(words);

        Map<String, Integer> counts = new WordCounter(wordCounterConfig).count("Home, Sweet Home");
        Assert.assertEquals(2, counts.size());
        Assert.assertEquals(2, counts.get("Home").intValue());
        Assert.assertEquals(1, counts.get("Sweet").intValue());
        Assert.assertEquals(null, counts.get("Work"));
    }

    @Test
    public void countSkipTest() {
        WordCounterConfig wordCounterConfig = new WordCounterConfig();
        wordCounterConfig.setCaseSensitive(false);
        WordCounterConfig.Words words = new WordCounterConfig.Words();
        words.setSkip(Arrays.asList("the", "an", "a"));
        wordCounterConfig.setWords(words);

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
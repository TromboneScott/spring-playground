package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WordCounter {
    private final WordCounterConfig wordCounterConfig;

    public WordCounter(WordCounterConfig wordCounterConfig) {
        this.wordCounterConfig = wordCounterConfig;
    }

    public Map<String, Integer> count(String string) {
        return Arrays.stream(string.replaceAll("\\p{Punct}", "").split(" "))
                .filter(word -> wordCounterConfig.isCaseSensitive() ?
                        !wordCounterConfig.getWords().getSkip().contains(word) :
                        wordCounterConfig.getWords().getSkip().stream().noneMatch(skip -> skip.equalsIgnoreCase(word)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(word -> 1)));
    }
}
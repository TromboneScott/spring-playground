package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {
    public Map<String, Integer> count(String string) {
        return Arrays.stream(string.replaceAll("\\p{Punct}", "").split(" ")).collect(
                Collectors.groupingBy(Function.identity(), Collectors.summingInt(word -> 1)));
    }
}
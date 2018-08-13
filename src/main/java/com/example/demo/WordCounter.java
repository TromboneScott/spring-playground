package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class WordCounter {
    @Bean
    public WordCounter getWordCounter(){
        return new WordCounter();
    }

    public Map<String, Integer> count(String string) {
        return Arrays.stream(string.replaceAll("\\p{Punct}","").split(" ")).collect(
                Collectors.groupingBy(Function.identity(), Collectors.summingInt(word -> 1)));
    }
}
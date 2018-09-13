package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public WordCounterConfig getWordCounterConfig(){
        return new WordCounterConfig();
    }
}
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordsController {
    @Autowired
    private  WordCounter wordCounter;

    @PostMapping("/count")
    public Map<String, Integer> count(@RequestBody String string) {
        return wordCounter.count(string);
    }
}
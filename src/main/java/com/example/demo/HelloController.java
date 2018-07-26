package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/math/pi")
    public String pi() {
        return String.valueOf(Math.PI);
    }

    @GetMapping("/math/calculate")
    public String calculate(@RequestParam(defaultValue = "add") String operation,
                            @RequestParam int x, @RequestParam int y) {
        return MathService.calculate(operation, x, y);
    }

    @GetMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> params) {
        return Optional.ofNullable(params.get("n")).map(MathService::sum).orElse("missing parameter: n");
    }

    @GetMapping("/math/volume/{length}/{width}/{height}")
    public String volume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        return MathService.volume(length, width, height);
    }
}
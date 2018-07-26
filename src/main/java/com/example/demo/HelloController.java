package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/math/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String area(AreaParams params) {
        return MathService.area(params);
    }
}
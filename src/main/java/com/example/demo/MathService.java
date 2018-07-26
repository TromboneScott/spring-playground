package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MathService {
    public static String calculate(String operation, int x, int y) {
        return "add".equals(operation) ? x + " + " + y + " = " + (x + y)
                : "subtract".equals(operation) ? x + " - " + y + " = " + (x - y)
                : "multiply".equals(operation) ? x + " * " + y + " = " + (x * y)
                : "divide".equals(operation) ? x + " / " + y + " = " + (x / y)
                : "Invalid operation: " + operation;
    }

    public static String sum(List<String> values) {
        return values.stream().collect(Collectors.joining(" + ")) + " = " +
                values.stream().mapToInt(Integer::parseInt).sum();
    }

    public static String volume(int length, int width, int height) {
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, length * width * height);
    }

    public static String area(AreaParams params) {
        if ("circle".equals(params.getType()) && params.getRadius() > 0)
            return "Area of a circle with a radius of " + params.getRadius() + " is " +
                    Math.round(Math.PI * Math.pow(params.getRadius(), 2) * 100000) / 100000.0;
        if ("rectangle".equals(params.getType()) && params.getWidth() > 0 && params.getHeight() > 0)
            return "Area of a " + params.getWidth() + "x" + params.getHeight() + " rectangle is " +
                    params.getWidth() * params.getHeight();
        return "Invalid";
    }
}
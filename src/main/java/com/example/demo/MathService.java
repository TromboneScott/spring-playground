package com.example.demo;

import java.util.List;
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
}
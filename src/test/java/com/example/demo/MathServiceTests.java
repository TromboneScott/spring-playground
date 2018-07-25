package com.example.demo;

import com.sun.tools.javac.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MathServiceTests {
    @Test
    public void testCalculate() {
        Assert.assertEquals("4 + 6 = 10", MathService.calculate("add", 4, 6));
        Assert.assertEquals("4 * 6 = 24", MathService.calculate("multiply", 4, 6));
        Assert.assertEquals("4 - 6 = -2", MathService.calculate("subtract", 4, 6));
        Assert.assertEquals("30 / 5 = 6", MathService.calculate("divide", 30, 5));
    }

    @Test
    public void testSum() {
        Assert.assertEquals("4 + 5 + 6 = 15", MathService.sum(List.of("4", "5", "6")));
    }
}
package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
        Assert.assertEquals("4 + 5 + 6 = 15", MathService.sum(Arrays.asList("4", "5", "6")));
    }

    @Test
    public void testVolume() {
        Assert.assertEquals("The volume of a 3x4x5 rectangle is 60",
                MathService.volume(3, 4, 5));
    }

    @Test
    public void testArea() {
        Assert.assertEquals("Area of a circle with a radius of 4 is 50.26548",
                MathService.area(new AreaParams("circle", 4, 0, 0)));
        Assert.assertEquals("Area of a 4x7 rectangle is 28",
                MathService.area(new AreaParams("rectangle", 0, 4, 7)));
        Assert.assertEquals("Invalid",
                MathService.area(new AreaParams("circle", 0, 0, 0)));
    }
}
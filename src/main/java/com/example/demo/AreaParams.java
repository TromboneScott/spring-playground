package com.example.demo;

public class AreaParams {
    private String type;
    private int radius;
    private int width;
    private int height;

    public AreaParams() {
    }

    public AreaParams(String type, int radius, int width, int height) {
        this.type = type;
        this.radius = radius;
        this.width = width;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public int getRadius() {
        return radius;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
package com.liu;

public abstract class TwoDShape {
    private double width;
    private double height;
    Colour colour;

    public TwoDShape() {
        this.width = 0;
        this.height = 0;
    }

    public TwoDShape(double width, double height, Colour colour) {
        this.width = width;
        this.height = height;
    }
    public abstract double getArea();

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public abstract String toString();

}

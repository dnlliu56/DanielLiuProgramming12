package com.liu;

public class TwoDShape {
    private double width;
    private double height;

    public TwoDShape(){
        width = 11.0;
        height = 12.0;
    }

    public TwoDShape(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getArea() {
        return width * height;
    }

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

    public String toString(){//overriding the toString() method
        return "The rectangle's area is " + getArea();
    }

}

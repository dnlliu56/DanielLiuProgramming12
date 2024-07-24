package com.liu;

public class Circle extends TwoDShape {
    final double PI = Math.PI;
    private double radius;
    private Colour colour;

    public Circle(double radius, Colour colour) {
        this.radius = radius;
        this.colour = colour;
    }

    @Override
    public double getArea() {
        return PI * Math.pow(radius, 2);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "The circle's area is " + getArea() + "\n"
                + "The radius is " + radius + "\n"
                + "The colour is " + colour.getName() + "\n";
    }
}
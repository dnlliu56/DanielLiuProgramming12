package com.liu;

public class Triangle extends TwoDShape {
    private double side1;
    private double side2;   //base of triangle
    private double side3;
    private double height;

    public Triangle(double width, double height) {
        this.side2 = width;
        this.height = height;
    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.height = heronsHeight();
    }

    @Override
    public double getArea() {
        return (side2 * height / 2);
    }

    public double heronsHeight() {
        double S = (side1 + side2 + side3) / 2;
        double A = Math.sqrt(S * (S - side1) * (S - side2) * (S - side3));
        double h = (2 * A) / side2;
        height = h;
        return h;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "The triangle's area is " + getArea() + "\n" + "The sides are " + side1 + ", " + side2 + ", " + side3 + "\n";
    }
}

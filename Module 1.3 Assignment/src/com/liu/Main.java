package com.liu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TwoDShape> shapes = new ArrayList<>();
        Triangle triangle = new Triangle(0, 0);

        shapes.add(new Triangle(3, 4, 5));
        shapes.add(new Circle(3));
        shapes.add(new Circle(12.3));
        shapes.add(new Triangle(11.6, 2.8));
        shapes.add(new Circle(7.91));
        shapes.add(new Triangle(1.333, 1.444, 1.555));

        ((Rotate) shapes.get(0)).rotate90();
        ((Rotate) shapes.get(3)).rotate180();
        ((Rotate) shapes.get(5)).rotate(45);

        for(TwoDShape s : shapes) {
            System.out.println(s);
        }
    }
}
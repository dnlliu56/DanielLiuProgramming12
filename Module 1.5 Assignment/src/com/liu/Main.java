package com.liu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TwoDShape> shapes = new ArrayList<>();

        shapes.add(new Triangle(3, 4, 5, Colour.RED));
        shapes.add(new Circle(3, Colour.NONE));
        shapes.add(new Circle(12.3, Colour.GREEN));
        shapes.add(new Triangle(11.6, 2.8, Colour.BLUE));
        shapes.add(new Circle(7.91, Colour.RED));
        shapes.add(new Triangle(1.333, 1.444, 1.555, Colour.GREEN));

        //Rotates triangle instances
        ((Rotate) shapes.get(0)).rotate90();
        ((Rotate) shapes.get(3)).rotate180();
        ((Rotate) shapes.get(5)).rotate(45);

        for(TwoDShape s : shapes) {
            System.out.println(s);
        }
    }
}
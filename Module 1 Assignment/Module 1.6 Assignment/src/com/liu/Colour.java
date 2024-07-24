package com.liu;

public enum Colour {
    RED("red"), GREEN("green"), BLUE("blue"), NONE("none");

    private final String COLOURNAME;

    Colour(String colourname) {
        COLOURNAME = colourname;
    }

    public String getName() {
        return this.COLOURNAME;
    }
}

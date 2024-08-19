package com.liu;

public enum Suit {
    SPADE("spades"), HEART("hearts"), DIAMOND("diamonds"), CLUB("clubs");

    private final String SUITNAME;

    Suit(String suitName) {
        SUITNAME = suitName;
    }

    public String getSuit() {
        return this.SUITNAME;
    }

}

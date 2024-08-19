package com.liu;

public enum Suit {
    SPADE("spades"), CLUB("clubs"), HEART("hearts"), DIAMOND("diamonds");

    private final String SUITNAME;

    Suit(String suitName) {
        SUITNAME = suitName;
    }

    public String getSuit() {
        return this.SUITNAME;
    }

}

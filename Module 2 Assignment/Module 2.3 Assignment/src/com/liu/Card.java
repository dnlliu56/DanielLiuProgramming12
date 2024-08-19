package com.liu;

public class Card {
    private int value;
    private boolean isFace;
    private Suit suit;

    public Card(int value, boolean isFace, Suit suit) {
        this.value = value;
        this.isFace = isFace;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFace() {
        return isFace;
    }

    public void setFace(boolean face) {
        isFace = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        String value;

        // Determine the card's value as a string based on its integer value
        switch(this.value) {
            case 1:
                value = "ace"; // Value 1 corresponds to Ace
                break;
            case 11:
                value = "jack"; // Value 11 corresponds to Jack
                break;
            case 12:
                value = "queen"; // Value 12 corresponds to Queen
                break;
            case 13:
                value = "king"; // Value 13 corresponds to King
                break;
            default:
                // For values 2-10, convert the integer value to a string
                value = "" + this.value;
        }

        // Return a string describing the card, including its value and suit
        return "The card is the " + value + " of " + suit.getSuit();
    }
}

package com.liu;

import java.util.Comparator;

public class SuitComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        // Compare the ordinal values of the suits of the two cards
        if(card1.getSuit().ordinal() < card2.getSuit().ordinal()) {
            return -1; // card1's suit is ranked lower, so it should come before card2
        }
        else if(card1.getSuit().ordinal() > card2.getSuit().ordinal()) {
            return 1; // card1's suit is ranked higher, so it should come after card2
        }
        // If the suits have the same ordinal value, consider the cards equal in terms of suit
        return 0;
    }
}
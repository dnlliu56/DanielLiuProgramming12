package com.liu;

import java.util.Comparator;

public class FaceComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        // Check if card1 is a face card and card2 is not
        if(card1.isFace() && !card2.isFace()) {
            return -1; // card1 should be ordered before card2
        }
        // Check if card2 is a face card and card1 is not
        else if(!card1.isFace() && card2.isFace()) {
            return 1; // card2 should be ordered before card1
        }
        // If both cards are either face cards or non-face cards, consider them equal
        return 0;
    }
}
package com.liu;

import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        LinkedList<Card> cardDeck = deck.createDeck();    // Standard deck of cards
        cardDeck = deck.shuffleDeck(cardDeck);    // Shuffle deck
        System.out.println("--------------- Shuffled Deck ---------------");
        for(Card card : cardDeck) {
            System.out.println(card);
        }
        System.out.println("\n--------------- Sorted by Suit ---------------");
        SuitComparator bySuit = new SuitComparator();
        Collections.sort(cardDeck, bySuit);
        for(Card card : cardDeck) {
            System.out.println(card);
        }
        System.out.println("\n--------------- Sorted by Face ---------------");
        FaceComparator byFace = new FaceComparator();
        Collections.sort(cardDeck, byFace);
        for(Card card : cardDeck) {
            System.out.println(card);
        }
    }
}
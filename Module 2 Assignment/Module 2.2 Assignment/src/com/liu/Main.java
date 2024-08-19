package com.liu;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        LinkedList<Card> cardDeck = deck.createDeck();    // Standard deck of cards
        cardDeck = deck.shuffleDeck(cardDeck);    // Shuffle deck
        LinkedList<Card> newDeck = deck.pollDeck(cardDeck, 7);    // New deck to store cards polled from cardDeck

        for(int i = 0; i < newDeck.size(); i++) {
            System.out.println(newDeck.get(i));
        }
    }
}
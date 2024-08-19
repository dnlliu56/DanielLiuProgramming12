package com.liu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Deck {
    private LinkedList<Card> cardDeck;

    public Deck() {
        cardDeck = createDeck();
    }

    /**
     * Creates a LinkedList of card objects for each card in a standard deck
     * @return a LinkedList containing card objects
     */
    public LinkedList<Card> createDeck() {
        List<Card> cardList = new ArrayList<>();
        // Creates cards by suit
        for(Suit suit : Suit.values()) {
            // Creates a card with each valid value
            for(int i = 1; i <= 13; i++) {
                // If card is a face card set isFace to true, otherwise set to false
                if (i > 10) {
                    cardList.add(new Card(i, true, suit));
                }
                else {
                    cardList.add(new Card(i, false, suit));
                }
            }
        }
        return new LinkedList<>(cardList);
    }

    /**
     * Takes a LinkedList deck of cards and polls the last cards into a new LinkedList deck.
     * @param deck containing all the cards.
     * @param newDeckSize the size that the new deck should be.
     * @return a LinkedList containing all the polled cards.
     */
    public LinkedList<Card> pollDeck(LinkedList<Card> deck, int newDeckSize) {
        // Create a new ArrayList to hold the cards for the new deck
        List<Card> newDeck = new ArrayList<>();
        // Loop to retrieve cards from the end of the original deck
        for(int i = 0; i < newDeckSize; i++) {
            // Remove the last card from the original deck and add it to the new deck
            newDeck.add(deck.pollLast());
        }
        // Return a new LinkedList created from the cards in the new deck
        return new LinkedList<>(newDeck);
    }

    /**
     * Takes a LinkedList deck of cards and shuffles the order of the cards.
     * @param deck containing the cards in original order.
     * @return a LinkedList deck in shuffled order.
     */
    public LinkedList<Card> shuffleDeck(LinkedList<Card> deck) {
        List<Card> shuffledDeck = new ArrayList<>(deck);
        Collections.shuffle(shuffledDeck);
        return new LinkedList<>(shuffledDeck);
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    public Card peekFirst() {
        return cardDeck.peekFirst();
    }

    public Card peekLast() {
        return cardDeck.peekLast();
    }
}

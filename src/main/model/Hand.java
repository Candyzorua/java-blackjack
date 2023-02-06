package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> contents;

    // EFFECTS: constructs an empty hand
    public Hand() {
        contents = new ArrayList<>();
    }

    // MODIFIES: this, cd
    // EFFECTS: adds a card to the hand from a given deck
    public void addCard(CardDeck cd) {
        Card newCard = cd.popCard();
        contents.add(newCard);
    }

    // MODIFIES: this
    // EFFECTS: adds a given card to the hand (for testing purposes)
    public void addCard(Card c) {
        contents.add(c);
    }

    // MODIFIES: this
    // EFFECTS: empties out the hand
    public void empty() {
        contents.clear();
    }

    // EFFECTS: calculates the number value of the hand according to blackjack rules
    public int calculateHand() {
        int sum = 0;
        for (Card c: contents) {
            sum += c.getValue();
        }
        return sum;
    }

    // getters
    public List<Card> getContents() {
        return contents;
    }
}

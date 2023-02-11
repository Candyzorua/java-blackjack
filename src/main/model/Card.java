package model;

public class Card {
    private final String suit;
    private final String label;
    private final int value;

    // EFFECTS: constructs a card with given suit, label, and value
    public Card(String suit, String label, int value) {
        this.suit = suit;
        this.label = label;
        this.value = value;
    }

    // EFFECTS: returns String representation of card
    public String cardToString() {
        return (label + " of " + suit);
    }

    // getters

    public String getSuit() {
        return suit;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}

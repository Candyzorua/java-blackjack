package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> contents;

    // EFFECTS: constructs a standard deck of cards with 52 cards
    public CardDeck() {
        contents = new ArrayList<>();
        String[] suits = {"spades", "hearts", "clubs", "diamonds"};
        String[] labels = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        for (String s : suits) {
            for (int i = 0; i < 13; i++) {
                Card card = new Card(s, labels[i], values[i]);
                contents.add(card);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a random card from the deck and returns it
    public Card popCard() {
        Collections.shuffle(contents);
        Card poppedCard = contents.get(0);
        contents.remove(0);
        return poppedCard;
    }

    // getters
    public int getSize() {
        return contents.size();
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {
    Card c1;
    Card c2;
    Card c3;
    Hand h1;
    Hand h2;
    Hand h3;
    CardDeck cd1;

    @BeforeEach
    public void setUp() {
        c1 = new Card("diamonds", "K", 10);
        c2 = new Card("spades", "A", 11);
        c3 = new Card("clubs", "2", 2);
        h1 = new Hand();
        h2 = new Hand();
        h3 = new Hand();
        cd1 = new CardDeck();
    }

    @Test
    public void testConstructor() {
        assertTrue(h1.getContents().isEmpty());
    }

    @Test
    public void testAddCard() {
        h1.addCard(c1);
        assertEquals(1, h1.getContents().size());
        h1.addCard(cd1);
        assertEquals(2, h1.getContents().size());
    }

    @Test
    public void testEmpty() {
        h1.addCard(c1);
        h1.addCard(c2);
        h1.empty();
        assertEquals(0, h1.getContents().size());
    }
}

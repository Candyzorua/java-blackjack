package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    Card c1;

    @BeforeEach
    public void setUp() {
        c1 = new Card("spades", "10", 10);
    }

    @Test
    public void testConstructor() {
        assertEquals("spades", c1.getSuit());
        assertEquals("10", c1.getLabel());
        assertEquals(10, c1.getValue());
    }

    @Test
    public void testCardToString() {
        assertEquals("10 of spades", c1.cardToString());
    }
}

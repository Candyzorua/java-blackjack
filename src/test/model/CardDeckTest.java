package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardDeckTest {
    private CardDeck testCD;

    @BeforeEach
    public void setUp() {
        testCD = new CardDeck();
    }

    @Test
    public void testConstructor() {
        assertEquals(52, testCD.getSize());
    }

    @Test
    public void testPopCard() {
        Card c1 = (testCD.popCard());
        assertEquals(51, testCD.getSize());
        boolean notEqual = true;
        for (int i = 0; i < 999; i++) {
            CardDeck testCD2 = new CardDeck();
            Card nc1 = testCD2.popCard();
            Card nc2 = testCD2.popCard();
            Card nc3 = testCD2.popCard();
            if (nc1.equals(nc2) | nc2.equals(nc3)) {
                notEqual = false;
            }
        }
        assertTrue(notEqual);
    }
}

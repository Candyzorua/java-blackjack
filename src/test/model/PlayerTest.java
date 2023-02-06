package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.RoundStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {
    private RegularPlayer p1;
    private Player p2;
    private Hand h1;
    private Card c1;
    private CardDeck cd1;

    @BeforeEach
    public void runBefore() {
        p1 = new RegularPlayer("Jin", 0);
        c1 = new Card("diamonds", "K", 10);
        h1 = new Hand();
        h1.addCard(c1);
        cd1 = new CardDeck();
        p2 = new Dealer("Mikayla", 19);
    }

    @Test
    public void testRegularPlayerConstructor() {
        assertEquals("Jin", p1.getName());
        assertEquals(0, p1.getScore());
        assertEquals(0, p1.getWager());
        assertEquals(0, p1.getHandSize());
        assertEquals(PENDING, p1.getStatus());
    }

    @Test
    public void testDealerConstructor() {
        assertEquals("Mikayla", p2.getName());
        assertEquals(19, p2.getScore());
        assertEquals(0, p2.getWager());
        assertEquals(0, p2.getHandSize());
        assertEquals(PENDING, p2.getStatus());
    }

    @Test
    public void testAddAndDeductScore() {
        p1.deductScore(1);
        assertEquals(-1, p1.getScore());
        p1.deductScore(1);
        assertEquals(-2, p1.getScore());
        p1.addScore(1);
        assertEquals(-1, p1.getScore());
        p1.addScore(1);
        assertEquals(0, p1.getScore());
    }

    @Test
    public void testReset() {
        p1.setWager(403);
        p1.setHand(h1);
        p1.resetPlayer();
        assertEquals(0, p1.getHandSize());
        assertEquals(0, p1.getWager());
    }

    @Test
    public void testDealInitialCards() {
        p1.dealInitialCards(cd1);
        assertEquals(2, p1.getHand().getContents().size());
    }

    @Test
    public void testDrawCard() {
        p1.drawCard(cd1);
        assertEquals(1, p1.getHand().getContents().size());
        p1.resetPlayer();
        p1.dealInitialCards(cd1);
        p1.drawCard(cd1);
        assertEquals(3, p1.getHand().getContents().size());
    }

    @Test
    public void testGetHandAsString() {
        p1.setHand(h1);
        assertEquals("K of diamonds | ", p1.getHandAsString());
        p1.getHand().addCard(c1);
        assertEquals("K of diamonds | K of diamonds | ", p1.getHandAsString());
    }
}
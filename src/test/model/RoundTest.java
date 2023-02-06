package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private Round r1;
    private Dealer d1;
    private RegularPlayer p2;
    private RegularPlayer p3;
    private List<RegularPlayer> regularPlayerList;
    Card c1;
    Card c2;
    Card c3;
    Hand h1;
    Hand h2;
    Hand h3;

    @BeforeEach
    public void runBefore() {
        d1 = new Dealer("Jin", 0);
        p2 = new RegularPlayer("Amy", 0);
        p3 = new RegularPlayer("Tracy", 0);
        regularPlayerList = new ArrayList<>();
        regularPlayerList.add(p2);
        regularPlayerList.add(p3);
        r1 = new Round(1, regularPlayerList, d1);
        c1 = new Card("diamonds", "K", 10);
        c2 = new Card("spades", "A", 11);
        c3 = new Card("clubs", "2", 2);
        h1 = new Hand();
        h2 = new Hand();
        h3 = new Hand();
    }

    @Test
    public void testConstructor() {
        assertEquals(1, r1.getRoundNumber());
        assertEquals("Amy", r1.getRegularPlayers().get(0).getName());
        assertEquals("Tracy", r1.getRegularPlayers().get(1).getName());
        assertEquals("Jin", r1.getDealer().getName());
        assertEquals(46, r1.getCd().getSize());
    }

    @Test
    public void testDealCardsToAllPlayersSetBlackjack() {
        for (int i = 0; i < 99999; i++) {
            d1 = new Dealer("Jin", 0);
            p2 = new RegularPlayer("Amy", 0);
            p3 = new RegularPlayer("Tracy", 0);
            regularPlayerList = new ArrayList<>();
            regularPlayerList.add(p2);
            regularPlayerList.add(p3);
            r1 = new Round(1, regularPlayerList, d1);
            for (Player p : regularPlayerList) {
                assertEquals(2, p.getHand().getContents().size());
                if (p.getHandSize() == 21 | p.getHandSize() == 22) {
                    assertSame(p.getStatus(), RoundStatus.BLACKJACK);
                }
            }
            if (r1.getDealer().getHandSize() == 21 | r1.getDealer().getHandSize() == 22) {
                assertSame(r1.getDealer().getStatus(), RoundStatus.BLACKJACK);
            }
        }
    }

    @Test
    public void testLetPlayerHit() {
        for (int i = 0; i < 99999; i++) {
            d1 = new Dealer("Jin", 0);
            p2 = new RegularPlayer("Amy", 0);
            p3 = new RegularPlayer("Tracy", 0);
            regularPlayerList = new ArrayList<>();
            regularPlayerList.add(p2);
            regularPlayerList.add(p3);
            r1 = new Round(1, regularPlayerList, d1);
            for (Player p : regularPlayerList) {
                if (p.getHandSize() < 21) {
                    r1.letPlayerHit(p);
                    assertEquals(3, p.getHand().getContents().size());
                    if (p.getHandSize() > 21) {
                        assertEquals(RoundStatus.BUST, p.getStatus());
                    }
                }
            }
            if (r1.getDealer().getHandSize() < 21) {
                r1.letPlayerHit(r1.getDealer());
                assertEquals(3, r1.getDealer().getHand().getContents().size());
                if (r1.getDealer().getHandSize() > 21) {
                    assertEquals(RoundStatus.BUST, r1.getDealer().getStatus());
                }
            }
        }
    }

    @Test
    public void testHandlePayoutsTypicalWithDealerBust() {
        p2.setWager(3);
        p3.setWager(7);

        // h2 busts w/ hand 22
        h2.addCard(c2);
        h2.addCard(c2);
        p2.setHand(h2);
        p2.setStatus(RoundStatus.BUST);

        // h3 stands w/ hand 20
        h3.addCard(c1);
        h3.addCard(c1);
        p3.setHand(h3);
        p3.setStatus(RoundStatus.STAND);

        // d1 busts w/ hand 22
        h1.addCard(c2);
        h1.addCard(c2);
        d1.setHand(h1);
        d1.setStatus(RoundStatus.BUST);

        r1.handlePayouts();
        assertEquals(-4, d1.getScore());
        assertEquals(-3, p2.getScore());
        assertEquals(7, p3.getScore());
    }

    @Test
    public void testHandlePayoutsTypicalWithDealerStand() {
        p2.setWager(3);
        p3.setWager(7);

        // h2 stand w/ hand 21
        h2.addCard(c1);
        h2.addCard(c2);
        p2.setHand(h2);
        p2.setStatus(RoundStatus.STAND);

        // h3 stands w/ hand 4
        h3.addCard(c3);
        h3.addCard(c3);
        p3.setHand(h3);
        p3.setStatus(RoundStatus.STAND);

        // d1 stands w/ hand 20
        h1.addCard(c1);
        h1.addCard(c1);
        d1.setHand(h1);
        d1.setStatus(RoundStatus.STAND);

        r1.handlePayouts();
        assertEquals(4, d1.getScore());
        assertEquals(3, p2.getScore());
        assertEquals(-7, p3.getScore());
    }

    @Test
    public void testHandlePayoutsWithTies() {
        p2.setWager(3);
        p3.setWager(7);

        // h2 stands w/ hand 20
        h2.addCard(c1);
        h2.addCard(c1);
        p2.setHand(h2);
        p2.setStatus(RoundStatus.STAND);

        // h3 stands w/ hand 20
        h3.addCard(c1);
        h3.addCard(c1);
        p3.setHand(h3);
        p3.setStatus(RoundStatus.STAND);

        // d1 stands w/ hand 20
        h1.addCard(c1);
        h1.addCard(c1);
        d1.setHand(h1);
        d1.setStatus(RoundStatus.STAND);

        r1.handlePayouts();
        assertEquals(0, d1.getScore());
        assertEquals(0, p2.getScore());
        assertEquals(0, p3.getScore());
    }

    @Test
    public void testHandlePayoutsWithBlackjack() {
        p2.setWager(3);
        p3.setWager(7);

        // p2 gets blackjack
        h2.addCard(c1);
        h2.addCard(c2);
        p2.setHand(h2);
        p2.setStatus(RoundStatus.BLACKJACK);

        // p3 busts w/ hand 22
        h3.addCard(c2);
        h3.addCard(c2);
        p3.setHand(h3);
        p3.setStatus(RoundStatus.BUST);

        // d1 stands w/ hand 20
        h1.addCard(c1);
        h1.addCard(c1);
        d1.setHand(h1);
        d1.setStatus(RoundStatus.STAND);

        r1.handlePayouts();
        assertEquals(6, p2.getScore());
        assertEquals(-7, p3.getScore());
        assertEquals(1, d1.getScore());
    }

    @Test
    public void testHandleDealerBlackjack() {
        p2.setWager(3);
        p3.setWager(7);

        // p2 gets blackjack
        h2.addCard(c1);
        h2.addCard(c2);
        p2.setHand(h2);
        p2.setStatus(RoundStatus.BLACKJACK);

        // p3 busts w/ hand 22
        h3.addCard(c2);
        h3.addCard(c2);
        p3.setHand(h3);
        p3.setStatus(RoundStatus.BUST);

        // d1 stands w/ hand 20
        h1.addCard(c1);
        h1.addCard(c2);
        d1.setHand(h1);
        d1.setStatus(RoundStatus.BLACKJACK);

        r1.handlePayouts();
        assertEquals(0, p2.getScore());
        assertEquals(-14, p3.getScore());
        assertEquals(14, d1.getScore());
    }
}



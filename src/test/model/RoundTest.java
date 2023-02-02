package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    private Round r1;
    private Player d1;
    private Player p2;
    private Player p3;
    private List<Player> regularPlayerList;

    @BeforeEach
    public void runBefore() {
        d1 = new Player("Jin", 0);
        p2 = new Player("Amy", 0);
        p3 = new Player("Tracy", 0);
        regularPlayerList = new ArrayList<>();
        regularPlayerList.add(p2);
        regularPlayerList.add(p3);
        r1 = new Round(1, regularPlayerList, d1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, r1.getRoundNumber());
        assertEquals("Amy", r1.getRegularPlayerList().get(0).getName());
        assertEquals("Tracy", r1.getRegularPlayerList().get(1).getName());
        assertEquals("Jin", r1.getDealer().getName());
    }

    @Test
    public void testRecordWager() {
        r1.recordWager(d1, 8);
        assertEquals(8, d1.getWager());
        r1.recordWager(d1, 9);
        assertEquals(9, d1.getWager());
    }

    @Test
    public void testRecordStatus() {
        r1.recordStatus(d1, RoundStatus.STAND);
        assertEquals(RoundStatus.STAND, d1.getStatus());
        r1.recordStatus(d1, RoundStatus.PENDING);
        assertEquals(RoundStatus.PENDING, d1.getStatus());
        r1.recordStatus(p2, RoundStatus.BUST);
        assertEquals(RoundStatus.BUST, p2.getStatus());
        r1.recordStatus(p2, RoundStatus.BLACKJACK);
        assertEquals(RoundStatus.BLACKJACK, p2.getStatus());
    }

    @Test
    public void testRecordHand() {
        r1.recordHand(d1, 20);
        assertEquals(20, d1.getHand());
        r1.recordHand(p2, 15);
        assertEquals(15, p2.getHand());
    }

    @Test
    public void testHandlePayoutsTypical() {
        r1.recordWager(p2, 3);
        r1.recordWager(p3, 7);
        r1.recordStatus(p2, RoundStatus.BUST);
        r1.recordStatus(p3, RoundStatus.STAND);
        r1.recordHand(p3, 19);
        r1.recordStatus(d1, RoundStatus.BUST);
        r1.handlePayouts();
        assertEquals(-4, d1.getScore());
        assertEquals(-3, p2.getScore());
        assertEquals(7, p3.getScore());
    }

    @Test
    public void testHandlePayoutsWithTies() {
        r1.recordWager(p2, 3);
        r1.recordWager(p3, 7);
        r1.recordStatus(p2, RoundStatus.STAND);
        r1.recordStatus(p3, RoundStatus.STAND);
        r1.recordStatus(d1, RoundStatus.STAND);
        r1.recordHand(p2, 19);
        r1.recordHand(p3, 19);
        r1.recordHand(d1, 19);
        r1.handlePayouts();
        assertEquals(0, d1.getScore());
        assertEquals(0, p2.getScore());
        assertEquals(0, p3.getScore());
    }

    @Test
    public void testHandlePayoutsWithBlackjack() {
        r1.recordWager(p2, 3);
        r1.recordWager(p3, 5);
        r1.recordStatus(p2, RoundStatus.BLACKJACK);
        r1.recordStatus(p3, RoundStatus.BUST);
        r1.recordStatus(d1, RoundStatus.BUST);
        r1.handlePayouts();
        assertEquals(-1, d1.getScore());
        assertEquals(6, p2.getScore());
        assertEquals(-5, p3.getScore());
    }
}



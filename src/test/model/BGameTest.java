package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BGameTest {
    private BGame g1;
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;
    private Player p6;

    @BeforeEach
    public void runBefore() {
        g1 = new BGame();
        p1 = new Player("Amy", 0);
        p2 = new Player("Leona", 0);
        p3 = new Player("Christy", 0);
        p4 = new Player("Anna", 0);
        p5 = new Player("Squidward", 0);
        p6 = new Player("Elsa", 0);
    }

    @Test
    public void testConstructor() {
        assertTrue(g1.getRegularPlayers().isEmpty());
        assertNull(g1.getDealer());
        assertEquals(0, g1.getNumOfRounds());
    }

    @Test
    public void testAddPlayer() {
        boolean result1 = g1.addPlayer(p1);
        assertEquals(1, g1.getRegularPlayers().size());
        assertTrue(result1);

        boolean result2 = g1.addPlayer(p2);
        assertEquals(2, g1.getRegularPlayers().size());
        assertTrue(result2);

        boolean result3 = g1.addPlayer(p1);
        assertEquals(2, g1.getRegularPlayers().size());
        assertFalse(result3);
    }

    @Test
    public void testRemovePlayerSuccessful() {
        g1.addPlayer(p1);
        g1.addPlayer(p2);
        g1.addPlayer(p3);
        g1.addPlayer(p4);
        g1.addPlayer(p5);
        g1.addPlayer(p6);
        boolean result1 = g1.removePlayer(p1);
        assertEquals(5, g1.getRegularPlayers().size());
        boolean result2 = g1.removePlayer(p2);
        assertEquals(4, g1.getRegularPlayers().size());
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testRemovePlayerFail() {
        boolean result1 = g1.removePlayer(p1);
        assertEquals(0, g1.getRegularPlayers().size());
        assertFalse(result1);

        g1.addPlayer(p1);
        boolean result2 = g1.removePlayer(p1);
        assertEquals(1, g1.getRegularPlayers().size());
        assertFalse(result2);

        g1.addPlayer(p2);
        g1.addPlayer(p3);
        g1.addPlayer(p4);
        g1.addPlayer(p5);
        g1.setPlayerAsDealer(p5);
        boolean result3 = g1.removePlayer(p5);
        assertEquals(4, g1.getRegularPlayers().size());
        assertFalse(result3);
    }

    @Test
    public void testSetPlayerAsDealerSuccessful() {
        g1.addPlayer(p1);
        g1.addPlayer(p3);
        assertTrue(g1.setPlayerAsDealer(p3));
        assertEquals(g1.getDealer().getName(), p3.getName());
        assertTrue(g1.setPlayerAsDealer(p1));
        assertEquals(g1.getDealer().getName(), p1.getName());
        assertEquals("Christy", g1.getRegularPlayers().get(0).getName());
    }

    @Test
    public void testSetPlayerAsDealerFail() {
        g1.addPlayer(p1);
        assertFalse(g1.setPlayerAsDealer(p3));
    }

    @Test
    public void testStartRound() {
        g1.addPlayer(p1);
        g1.addPlayer(p2);
        g1.addPlayer(p3);
        g1.addPlayer(p4);
        g1.addPlayer(p5);
        g1.setPlayerAsDealer(p1);
        Round round = g1.startRound();
        assertEquals(4, round.getRegularPlayers().size());
        assertEquals("Amy", round.getDealer().getName());
        assertEquals(1, g1.getNumOfRounds());
    }
}

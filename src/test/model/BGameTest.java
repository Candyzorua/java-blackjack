package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BGameTest {
    private BGame g1;
    private Player p1;
    private Player p2;
    private Player p3;

    @BeforeEach
    public void runBefore() {
        g1 = new BGame();
        p1 = new Player("Amy", 0);
        p2 = new Player("Leona", 0);
        p3 = new Player("Christy", 0);
    }

    @Test
    public void testConstructor() {
        assertTrue(g1.getRegularPlayerList().isEmpty());
        assertNull(g1.getDealer());
        assertEquals(1, g1.getNumOfRounds());
    }

    @Test
    public void testAddPlayer() {
        boolean result1 = g1.addPlayer(p1);
        assertEquals(1, g1.getRegularPlayerList().size());
        assertTrue(result1);

        boolean result2 = g1.addPlayer(p2);
        assertEquals(2, g1.getRegularPlayerList().size());
        assertTrue(result2);

        boolean result3 = g1.addPlayer(p1);
        assertEquals(2, g1.getRegularPlayerList().size());
        assertFalse(result3);
    }

    @Test
    public void testRemovePlayer() {
        g1.addPlayer(p1);
        g1.addPlayer(p2);
        boolean result1 = g1.removePlayer(p1);
        assertEquals(1, g1.getRegularPlayerList().size());
        boolean result4 = g1.removePlayer(p2);
        assertEquals(0, g1.getRegularPlayerList().size());
        assertTrue(result1);
        assertTrue(result4);

        boolean result2 = g1.removePlayer(p1);
        assertEquals(0, g1.getRegularPlayerList().size());
        assertFalse(result2);
    }

    @Test
    public void testSetPlayerAsDealer() {
        g1.addPlayer(p1);
        g1.addPlayer(p3);
        g1.setPlayerAsDealer(p3);
        assertTrue(g1.getDealer().equals(p3));
        g1.setPlayerAsDealer(p1);
        assertTrue(g1.getDealer().equals(p1));
        assertEquals("Christy", g1.getRegularPlayerList().get(0).getName());
    }
}

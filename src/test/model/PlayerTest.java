package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.RoundStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {
    private Player p1;

    @BeforeEach
    public void runBefore() {
        p1 = new Player("Jin", 0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Jin", p1.getName());
        assertEquals(0, p1.getScore());
        assertEquals(0, p1.getWager());
        assertEquals(0, p1.getHand());
        assertEquals(PENDING, p1.getStatus());
    }
}
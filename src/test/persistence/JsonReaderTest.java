package persistence;

import exceptions.NotEnoughPlayers;
import model.BGame;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BGame bg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNotEnoughPlayersGame() {
        JsonReader reader = new JsonReader("./data/testReaderNotEnoughPlayersGame.json");
        try {
            BGame bg = reader.read();
            assertEquals(1, bg.getNumPlayers());
            assertEquals(0, bg.getNumOfRounds());
            Player firstPlayer = bg.getRegularPlayers().get(0);
            checkPlayer("Jin", 0, firstPlayer);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReader4RoundsGame() {
        JsonReader reader = new JsonReader("./data/testReader4RoundsGame.json");
        try {
            BGame bg = reader.read();
            assertEquals(5, bg.getNumPlayers());
            assertEquals(4, bg.getNumOfRounds());
            Player firstPlayer = bg.getRegularPlayers().get(0);
            checkPlayer("Jin", -100, firstPlayer);
            Player secondPlayer = bg.getRegularPlayers().get(1);
            checkPlayer("Timothy", -200, secondPlayer);
            Player thirdPlayer = bg.getRegularPlayers().get(2);
            checkPlayer("Tracy", 300, thirdPlayer);
            Player fourthPlayer = bg.getRegularPlayers().get(3);
            checkPlayer("Oliver", 0, fourthPlayer);
            Player fifthPlayer = bg.getRegularPlayers().get(4);
            checkPlayer("Munn", 0, fifthPlayer);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderDefaultGame() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultGame.json");
        try {
            BGame bg = reader.read();
            assertEquals(5, bg.getNumPlayers());
            assertEquals(0, bg.getNumOfRounds());
            Player firstPlayer = bg.getRegularPlayers().get(0);
            checkPlayer("Jin", 0, firstPlayer);
            Player secondPlayer = bg.getRegularPlayers().get(1);
            checkPlayer("Mikayla", 0, secondPlayer);
            Player thirdPlayer = bg.getRegularPlayers().get(2);
            checkPlayer("Victor", 0, thirdPlayer);
            Player fourthPlayer = bg.getRegularPlayers().get(3);
            checkPlayer("Leona", 0, fourthPlayer);
            Player fifthPlayer = bg.getRegularPlayers().get(4);
            checkPlayer("Amy", 0, fifthPlayer);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

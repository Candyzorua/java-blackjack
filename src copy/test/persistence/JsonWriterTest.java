package persistence;

import model.BGame;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BGame bg = new BGame();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriter4RoundsGame() {
        try {
            BGame bg = new BGame();
            bg.addPlayer(new Player("Jin", -100));
            bg.addPlayer(new Player("Timothy", -200));
            bg.addPlayer(new Player("Tracy", 300));
            bg.addPlayer(new Player("Oliver", 0));
            Player dealer = new Player("Munn", 0);
            bg.addPlayer(dealer);
            bg.setPlayerAsDealer(dealer);
            JsonWriter writer = new JsonWriter("./data/testWriter4RoundsGame.json");
            writer.open();
            writer.write(bg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReader4RoundsGame.json");
            bg = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}


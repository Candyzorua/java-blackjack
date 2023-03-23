package ui;

import exceptions.NotEnoughPlayers;
import model.BGame;
import model.Player;
import persistence.JsonReader;

import java.io.IOException;

public class GameLoader {
    private final JsonReader jsonReader;
    private static final String JSON_STORE = "./data/game.json";

    public GameLoader() {
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: bg
    // EFFECTS: loads game from file and automatically sets the last player as the dealer
    //          if loaded games has less than the min. amount of players, default players loaded
    public void loadGame(BGame bg) {
        try {
            bg = jsonReader.read();
            if (bg.getNumPlayers() < BGame.MIN_PLAYERS) {
                throw new NotEnoughPlayers();
            }
            System.out.println("Loaded game from " + JSON_STORE + ".");
            Player lastPlayer = bg.getRegularPlayers().get(bg.getNumPlayers() - 1);
            bg.setPlayerAsDealer(lastPlayer);
            System.out.println("The last player has been set as the dealer by default.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + ".");
        } catch (NotEnoughPlayers nep) {
            System.out.println("Loaded game does not have enough players.");
            System.out.println("Loading default players instead...");
            loadDefaultPlayers(bg);
        }
    }

    // MODIFIES: bg
    // EFFECTS: loads the default players into the game
    public void loadDefaultPlayers(BGame bg) {
        Player p1 = new Player("Jin", 0);
        Player p2 = new Player("Mikayla", 0);
        Player p3 = new Player("Victor", 0);
        Player p4 = new Player("Leona", 0);
        Player p5 = new Player("Amy", 0);
        bg.addPlayer(p1);
        bg.addPlayer(p2);
        bg.addPlayer(p3);
        bg.addPlayer(p4);
        bg.addPlayer(p5);
        bg.setPlayerAsDealer(p5);
    }
}

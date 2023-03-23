package ui;

import model.BGame;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

/**
 * Handles saving of game
 */

public class GameSaver {
    private static final String JSON_STORE = "./data/game.json";
    private final JsonWriter jsonWriter;

    public GameSaver() {
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // EFFECTS: saves the game to file
    public void saveGame(BGame bg) {
        try {
            jsonWriter.open();
            jsonWriter.write(bg);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}

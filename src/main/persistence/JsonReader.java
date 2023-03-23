package persistence;

import model.BGame;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * A reader that reads a previously played game stored as JSON data in a separate file
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads BGame from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public BGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BGame from JSON object and returns it
    private BGame parseBGame(JSONObject jsonObject) {
        int numOfRounds = Integer.parseInt(jsonObject.getString("numOfRounds"));
        BGame bg = new BGame(numOfRounds);
        addSavedPlayers(bg, jsonObject);
        return bg;
    }

    // MODIFIES: bg
    // EFFECTS: parses players from JSON object and adds them to BGame
    private void addSavedPlayers(BGame bg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addSavedPlayer(bg, nextThingy);
        }
    }

    // MODIFIES: bg
    // EFFECTS: parses a single player from JSON object and adds it to BGame
    private void addSavedPlayer(BGame bg, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int score = Integer.parseInt(jsonObject.getString("score"));
        Player player = new Player(name, score);
        bg.addPlayer(player);
    }
}
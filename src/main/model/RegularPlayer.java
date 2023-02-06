package model;

public class RegularPlayer extends Player {

    // REQUIRES: playerName is a non-empty String
    // EFFECTS: constructs a regular player with given name and score,
    //          pending status and zero wager
    public RegularPlayer(String playerName, int score) {
        super(playerName, score);
    }
}

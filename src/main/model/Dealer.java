package model;

public class Dealer extends Player {

    // REQUIRES: playerName is a non-empty String
    // EFFECTS: constructs a regular dealer with given name and score,
    //          pending status and zero wager
    public Dealer(String playerName, int score) {
        super(playerName, score);
    }
}

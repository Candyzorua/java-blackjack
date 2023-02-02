package model;

public class Player {
    private String name;
    private int score;
    private RoundStatus status;
    private int hand;
    private int wager;

    // REQUIRES: playerName is a non-empty String
    // EFFECTS: constructs a player with a given name, with zero score, that is not a dealer,
    //          that doesn't have blackjack, and has pending status
    public Player(String playerName, int score) {
        this.name = playerName;
        this.score = 0;
        this.wager = 0;
        this.hand = 0;
        this.status = RoundStatus.PENDING;
    }

    // MODIFIES: this
    // EFFECTS: adds a given score to a player's score
    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    // MODIFIES: this
    // EFFECTS: deducts a given score from a player's score
    public void deductScore(int scoreToDeduct) {
        score -= scoreToDeduct;
    }

    // MODIFIES: this
    // EFFECTS: resets a player's wager to 0 and status to pending
    public void resetPlayer() {
        this.wager = 0;
        this.hand = 0;
        this.status = RoundStatus.PENDING;
    }

    // getters

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public RoundStatus getStatus() {
        return status;
    }

    public int getHand() {
        return hand;
    }

    public int getWager() {
        return wager;
    }


    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(RoundStatus status) {
        this.status = status;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }
}

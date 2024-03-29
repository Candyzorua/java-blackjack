package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * A single player in Blackjack
 */

public class Player implements Writable {
    private final String name;
    private int score;
    private RoundStatus status;
    private Hand hand;
    private int wager;

    // REQUIRES: playerName is a non-empty String
    // EFFECTS: constructs a player with given name and score,
    //          pending status and zero wager
    public Player(String playerName, int score) {
        this.name = playerName;
        this.score = score;
        this.wager = 0;
        this.hand = new Hand();
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
    // EFFECTS: resets a player's wager to 0, hand to empty, and status to pending
    public void resetPlayer() {
        this.wager = 0;
        hand.empty();
        this.status = RoundStatus.PENDING;
    }

    // MODIFIES: this, cd
    // EFFECTS: gets two cards from a given deck
    public void dealInitialCards(CardDeck cd) {
        hand.addCard(cd);
        hand.addCard(cd);
        EventLog.getInstance().logEvent(new Event("Player " + name + " dealt initial hand."));
    }

    // MODIFIES: this, cd
    // EFFECTS: gets a single card from a given deck
    public void drawCard(CardDeck cd) {
        hand.addCard(cd);
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

    public Hand getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.calculateHand();
    }

    // EFFECTS: displays the entire hand in string format
    public String getHandAsString() {
        StringBuilder result = new StringBuilder();
        for (Card c: hand.getContents()) {
            result.append(c.cardToString()).append(" | ");
        }
        return result.toString();
    }

    public int getWager() {
        return wager;
    }


    // setters

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setStatus(RoundStatus status) {
        this.status = status;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    // EFFECTS: converts the player into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("score", Integer.toString(score));
        return json;
    }
}

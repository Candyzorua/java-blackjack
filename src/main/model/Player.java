package model;

public class Player {
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

    // MODIFIES: this
    // EFFECTS: gets two cards from a given deck
    public void dealInitialCards(CardDeck cd) {
        hand.addCard(cd);
        hand.addCard(cd);
    }

    // MODIFIES: this
    // EFFECTS: gets a single card
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

    public String getHandAsString() {
        String result = "";
        for (Card c: hand.getContents()) {
            result += (c.cardToString() + " | ");
        }
        return result;
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

}

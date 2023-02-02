package model;

import java.util.ArrayList;
import java.util.List;

public class BGame {

    private List<Player> regularPlayerList;
    private Player dealer;
    private int numOfRounds = 0;
    private static final int MINPLAYERS = 4;

    // EFFECTS: constructs a new game of blackjack with an empty list of players
    public BGame() {
        regularPlayerList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a given player to the game
    //          fails if given player has the same name with another player or the dealer
    //          return true if success, false if failure
    public boolean addPlayer(Player player) {
        if (isPlayerWithNameIn(player.getName())) {
            return false;
        } else {
            regularPlayerList.add(player);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a given non-dealer player from the game
    //          fails if player is not in the game
    //          fails if try to decrease number of regular players < MINPLAYERS
    //          return true if success, false if failure
    public boolean removePlayer(Player player) {
        if (!regularPlayerList.contains(player) | (regularPlayerList.size() <= MINPLAYERS)) {
            return false;
        } else {
            regularPlayerList.remove(player);
            return true;
        }
    }

    // EFFECTS: returns true if a player with the given name is in the game
    public boolean isPlayerWithNameIn(String name) {
        for (Player p: regularPlayerList) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: returns a new round of blackjack with given players and dealer
    public Round startRound() {
        numOfRounds++;
        Round rd = new Round(numOfRounds, regularPlayerList, dealer);
        return rd;
    }

    // getters

    public List<Player> getRegularPlayerList() {
        return regularPlayerList;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public Player getDealer() {
        return dealer;
    }

    // setters

    // MODIFIES: this
    // EFFECTS: sets a regular player as dealer
    //          the existing dealer becomes a regular player
    //          if successful return true, if fail return false
    public boolean setPlayerAsDealer(Player player) {
        if (!regularPlayerList.contains(player)) {
            return false;
        } else if (dealer == null) {
            dealer = player;
            regularPlayerList.remove(player);
            return true;
        } else {
            Player newPlayer = new Player(dealer.getName(), dealer.getScore());
            regularPlayerList.remove(player);
            regularPlayerList.add(newPlayer);
            dealer = player;
            return true;
        }
    }
}

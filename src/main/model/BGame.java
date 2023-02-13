package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A single game of Blackjack
 */

public class BGame implements Playable {

    private List<RegularPlayer> regularPlayerList;
    private Player dealer;
    private int numOfRounds = 0;
    private static final int MIN_PLAYERS = 4;

    // EFFECTS: constructs a new game of blackjack with an empty list of players
    public BGame() {
        regularPlayerList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a given player to the game
    //          fails if given player has the same name with another player or the dealer
    //          return true if success, false if failure
    public boolean addPlayer(RegularPlayer player) {
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
    //          fails if try to decrease number of regular players < MIN_PLAYERS
    //          return true if success, false if failure
    public boolean removePlayer(RegularPlayer player) {
        if (!regularPlayerList.contains(player) | (regularPlayerList.size() <= MIN_PLAYERS)) {
            return false;
        } else {
            regularPlayerList.remove(player);
            return true;
        }
    }

    // EFFECTS: returns true if a player with the given name is in the game
    public boolean isPlayerWithNameIn(String name) {
        for (RegularPlayer p: regularPlayerList) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: returns a new round of blackjack with given players and dealer,
    //          with all player and dealer hands and wagers set to the initial condition
    //          number of rounds in the game is incremented
    public Round startRound() {
        numOfRounds++;
        for (RegularPlayer p: regularPlayerList) {
            p.resetPlayer();
        }
        dealer.resetPlayer();
        return new Round(numOfRounds, regularPlayerList, dealer);
    }

    // getters

    @Override
    public List<RegularPlayer> getRegularPlayers() {
        return regularPlayerList;
    }

    @Override
    public Player getDealer() {
        return dealer;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    // setters

    // MODIFIES: this
    // EFFECTS: sets a regular player as dealer
    //          the existing dealer becomes a regular player
    //          fails if the given player is not in the list of players
    //          if successful return true, if fail return false
    public boolean setPlayerAsDealer(RegularPlayer player) {
        if (!regularPlayerList.contains(player)) {
            return false;
        } else if (dealer == null) {
            regularPlayerList.remove(player);
            dealer = new Player(player.getName(), player.getScore());
            return true;
        } else {
            RegularPlayer newPlayer = new RegularPlayer(dealer.getName(), dealer.getScore());
            regularPlayerList.add(newPlayer);
            regularPlayerList.remove(player);
            dealer = new Player(player.getName(), player.getScore());
            return true;
        }
    }
}

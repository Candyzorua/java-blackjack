package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * A single game of Blackjack
 */

public class BGame implements Playable, Writable {

    private List<Player> regularPlayerList;
    private Player dealer;
    private int numOfRounds;
    public static final int MIN_PLAYERS = 4;

    // EFFECTS: constructs a new game of blackjack with an empty list of players
    public BGame() {
        numOfRounds = 0;
        regularPlayerList = new ArrayList<>();
    }

    // EFFECTS: constructs a new game of blackjack with an empty list of players and given number of rounds
    public BGame(int n) {
        numOfRounds = n;
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
    //          fails if try to decrease number of regular players < MIN_PLAYERS
    //          return true if success, false if failure
    public boolean removePlayer(Player player) {
        if (!regularPlayerList.contains(player) | (regularPlayerList.size() <= MIN_PLAYERS)) {
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
    // EFFECTS: returns a new round of blackjack with given players and dealer,
    //          with all player and dealer hands and wagers set to the initial condition
    //          number of rounds in the game is incremented
    public Round startRound() {
        numOfRounds++;
        for (Player p: regularPlayerList) {
            p.resetPlayer();
        }
        dealer.resetPlayer();
        return new Round(numOfRounds, regularPlayerList, dealer);
    }

    // MODIFIES: this
    // EFFECTS: sets a regular player as dealer
    //          the existing dealer becomes a regular player
    //          fails if the given player is not in the list of players
    //          if successful return true, if fail return false
    public boolean setPlayerAsDealer(Player player) {
        if (!regularPlayerList.contains(player)) {
            return false;
        } else if (dealer == null) {
            regularPlayerList.remove(player);
            dealer = new Player(player.getName(), player.getScore());
            return true;
        } else {
            Player newPlayer = new Player(dealer.getName(), dealer.getScore());
            regularPlayerList.add(newPlayer);
            regularPlayerList.remove(player);
            dealer = new Player(player.getName(), player.getScore());
            return true;
        }
    }

    // EFFECTS: returns BGame data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numOfRounds", Integer.toString(numOfRounds));
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns all players including dealer as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : regularPlayerList) {
            jsonArray.put(p.toJson());
        }
        jsonArray.put(dealer.toJson());
        return jsonArray;
    }

    // getters

    @Override
    public List<Player> getRegularPlayers() {
        return regularPlayerList;
    }

    public int getNumPlayers() {
        return regularPlayerList.size();
    }

    @Override
    public Player getDealer() {
        return dealer;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }
}

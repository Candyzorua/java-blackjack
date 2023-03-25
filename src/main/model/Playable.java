package model;

import java.util.List;

public interface Playable {
    // EFFECTS: get all non-dealer players
    List<Player> getRegularPlayers();

    // EFFECTS: get the dealer
    Player getDealer();

//    boolean addPlayer(Player player);
//
//    boolean removePlayer(Player player);
//
//    Player selectPlayer(String name);
//
//    boolean setPlayerAsDealer(Player toSetAsDealer);
//
//    Round startRound();
}

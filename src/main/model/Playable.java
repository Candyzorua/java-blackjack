package model;

import java.util.List;

public interface Playable {
    // EFFECTS: get all non-dealer players
    List<Player> getRegularPlayers();

    // EFFECTS: get the dealer
    Player getDealer();
}

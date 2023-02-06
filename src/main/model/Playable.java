package model;

import java.util.List;

public interface Playable {
    List<RegularPlayer> getRegularPlayers();

    Dealer getDealer();
}

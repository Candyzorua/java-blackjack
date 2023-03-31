package ui.configmenu;

import model.Player;

import java.util.List;

/**
 * Can be repopulated
 */

public interface Populatable {

    //MODIFIES: this
    //EFFECTS: clears and repopulates table with given list of players
    void repopulate(List<Player> players);
}

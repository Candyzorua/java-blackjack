package ui.configmenu;

import model.Player;

import javax.swing.*;
import java.util.List;

/**
 *  Dropdown menu for selection of players
 */

public class PlayerSelector extends JComboBox<String> implements Populatable {
    DefaultComboBoxModel<String> model;

    // EFFECTS: constructs an empty player selector dropdown menu
    public PlayerSelector() {
        super();
        model = new DefaultComboBoxModel<>();
        this.setModel(model);
        this.setBounds(50, 100, 90, 20);
    }

    // MODIFIES: this
    // EFFECTS: clears and repopulates player selector with given list of players
    public void repopulate(List<Player> players) {
        clearPlayers();
        addPlayers(players);
    }

    // MODIFIES: this
    // EFFECTS: adds players from the given list to the player selector
    private void addPlayers(List<Player> players) {
        for (Player p: players) {
            model.addElement(p.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the player selector
    private void clearPlayers() {
        model.removeAllElements();
    }
}

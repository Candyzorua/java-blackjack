package gui.configmenu;

import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelector extends JComboBox<String> implements Populatable {
    DefaultComboBoxModel<String> model;

    public PlayerSelector() {
        super();
        model = new DefaultComboBoxModel<>();
        this.setModel(model);
        this.setBounds(50, 100, 90, 20);
    }

    //MODIFIES: this
    //EFFECTS: clears and repopulates table with given list of players
    public void repopulate(List<Player> players) {
        clearPlayers();
        addPlayers(players);
    }

    //MODIFIES: this
    //EFFECTS: adds players from the given list to the JComboBox
    private void addPlayers(List<Player> players) {
        for (Player p: players) {
            model.addElement(p.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: clears table
    private void clearPlayers() {
        model.removeAllElements();
    }
}

package ui.configmenu;

import model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Table with player name and score
 */

public class PlayerTable extends JTable implements Populatable {
    String[][] data = {};
    String[] column = {"NAME", "SCORE"};
    DefaultTableModel model;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    // EFFECTS: sets the table model and size
    public PlayerTable() {
        super();
        model = new DefaultTableModel(data, column);
        this.setModel(model);
        this.setBounds(10, 10, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: clears and repopulates table with given list of players
    public void repopulate(List<Player> players) {
        clearPlayers();
        addPlayers(players);
    }

    // MODIFIES: this
    // EFFECTS: adds players from the given list to the table
    private void addPlayers(List<Player> players) {
        for (Player p: players) {
            model.addRow(new Object[]{p.getName(), p.getScore()});
        }
    }

    // MODIFIES: this
    // EFFECTS: clears table
    private void clearPlayers() {
        model.setRowCount(0);
    }
}


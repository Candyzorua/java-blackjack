package ui.gameui;

import model.Card;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Displays a single player's current hand
 */

public class HandTable extends JTable {
    String[][] data = {};
    String[] column = {"HAND"};
    DefaultTableModel model;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    public HandTable(List<Card> handList) {
        super();
        model = new DefaultTableModel(data, column);
        this.setModel(model);
        this.setBounds(10, 10, WIDTH, HEIGHT);
        this.repopulate(handList);
    }

    // MODIFIES: this
    // EFFECTS: clears table and then fills it
    public void repopulate(List<Card> handList) {
        model.setRowCount(0);
        for (Card c: handList) {
            model.addRow(new Object[]{c.cardToString()});
        }
    }
}

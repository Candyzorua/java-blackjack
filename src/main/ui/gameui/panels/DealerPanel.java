package ui.gameui.panels;

import model.Player;

import javax.swing.*;

/**
 * A panel for the round dealer
 */

public class DealerPanel extends PlayerPanel {
    public DealerPanel(Player player) {
        super(player);
    }

    // MODIFIES: this
    // EFFECTS: adds panel text relevant to the dealer
    @Override
    public void addLabels() {
        JLabel dealerLabel = new JLabel("DEALER");
        JLabel nameLabel = new JLabel("NAME: " + player.getName());
        JLabel scoreLabel = new JLabel("SCORE: " + player.getScore());
        statusLabel = new JLabel("STATUS: " + player.getStatus());
        this.add(dealerLabel);
        this.add(nameLabel);
        this.add(scoreLabel);
        this.add(statusLabel);
    }
}

package gui.gameui.panels;

import model.Player;

import javax.swing.*;

public class DealerPanel extends PlayerPanel {
    public DealerPanel(Player player) {
        super(player);
    }

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

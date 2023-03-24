package gui.gameui.panels;

import gui.gameui.HandTable;
import model.Card;
import model.CardDeck;
import model.Hand;
import model.Player;

import javax.swing.*;

public class RegularPlayerPanel extends PlayerPanel {
    public RegularPlayerPanel(Player player) {
        super(player);
    }

    public void addLabels() {
        JLabel nameLabel = new JLabel("NAME: " +  player.getName());
        JLabel scoreLabel = new JLabel("SCORE: " + player.getScore());
        JLabel wagerLabel = new JLabel("WAGER: " + player.getWager());
        statusLabel = new JLabel("STATUS: " + player.getStatus());
        this.add(nameLabel);
        this.add(scoreLabel);
        this.add(wagerLabel);
        this.add(statusLabel);
    }
}

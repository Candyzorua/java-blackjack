package ui.closingpanel;

import ui.BGameUI;
import ui.configmenu.PlayerStatsPanel;
import model.BGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel seen after a round
 */

public class ClosingPanel extends JPanel implements ActionListener {
    private final JButton saveButton;
    private final JButton quitButton;
    private final BGameUI gui;

    // EFFECTS: constructs a panel using the given BGame and BGameUI
    public ClosingPanel(BGame bg, BGameUI gui) {
        this.gui = gui;

        saveButton = new JButton("Save game");
        quitButton = new JButton("Quit");
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
        this.add(saveButton);
        this.add(quitButton);
        setRoundAndResultsPanel(bg);
    }

    // EFFECTS: saves the game when save button is clicked
    //          exits the game when exit button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            gui.saveGame();
        } else if (e.getSource() == quitButton) {
            gui.exitGame();
        }
    }

    // EFFECTS: creates, loads, and renders results of round
    public void setRoundAndResultsPanel(BGame bg) {
        PlayerStatsPanel resultsPanel = new PlayerStatsPanel(bg);
        resultsPanel.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        this.add(resultsPanel);
    }
}

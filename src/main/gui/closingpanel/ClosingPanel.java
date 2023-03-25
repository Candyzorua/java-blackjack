package gui.closingpanel;

import gui.BGameUI;
import gui.configmenu.PlayerStatsPanel;
import model.BGame;
import model.Round;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClosingPanel extends JPanel implements ActionListener {
    private final JButton saveButton;
    private final JButton quitButton;
    private final BGameUI gui;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            gui.saveGame();
        } else if (e.getSource() == quitButton) {
            gui.exitGame();
        }
    }

    public void setRoundAndResultsPanel(BGame bg) {
        PlayerStatsPanel resultsPanel = new PlayerStatsPanel(bg);
        resultsPanel.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        this.add(resultsPanel);
    }
}

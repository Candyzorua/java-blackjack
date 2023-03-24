package gui.closingpanel;

import gui.BGameUI;
import gui.configmenu.PlayerStatsPanel;
import model.Round;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClosingPanel extends JPanel implements ActionListener {
    private final JButton saveButton;
    private final JButton quitButton;
    private final BGameUI gui;

    public ClosingPanel(Round round, BGameUI gui) {
        this.gui = gui;

        saveButton = new JButton("Save game");
        quitButton = new JButton("Quit");
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
        this.add(saveButton);
        this.add(quitButton);
        setRoundAndResultsPanel(round);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            gui.saveGame();
        } else if (e.getSource() == quitButton) {
            gui.exitGame();
        }
    }

    public void setRoundAndResultsPanel(Round round) {
        PlayerStatsPanel resultsPanel = new PlayerStatsPanel(round);
        resultsPanel.refreshPlayers(round.getRegularPlayers(), round.getDealer());
        this.add(resultsPanel);
    }
}

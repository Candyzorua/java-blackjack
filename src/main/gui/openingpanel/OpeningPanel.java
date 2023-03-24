package gui.openingpanel;

import gui.BGameUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningPanel extends JPanel implements ActionListener {
    private final JButton startNewGame;
    private final JButton loadGame;
    private final BGameUI gui;

    public OpeningPanel(BGameUI gui) {
        this.gui = gui;
        startNewGame = new JButton("Start new game");
        loadGame = new JButton("Load previous game");
        startNewGame.addActionListener(this);
        loadGame.addActionListener(this);
        this.add(startNewGame);
        this.add(loadGame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startNewGame) {
            gui.initializeNewGame();
        } else if (e.getSource() == loadGame) {
            gui.initializeOldGame();
        }
    }
}

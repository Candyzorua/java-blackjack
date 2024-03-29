package ui.openingpanel;

import ui.BGameUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Opening panel of the game
 */

public class OpeningPanel extends JPanel implements ActionListener {
    private final JButton startNewGame;
    private final JButton loadGame;
    private final BGameUI gui;

    // EFFECTS: constructs an opening panel which lets player load game or start anew
    //          displays an image of the game logo
    public OpeningPanel(BGameUI gui) {
        this.gui = gui;
        this.setLayout(new FlowLayout());
        startNewGame = new JButton("Start new game");
        loadGame = new JButton("Load previous game");
        startNewGame.addActionListener(this);
        loadGame.addActionListener(this);

        try {
            BufferedImage logo = ImageIO.read(new File("src/main/graphics/javablackjacklogo.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(logo));
            this.add(picLabel);
        } catch (IOException ioe) {
            throw new RuntimeException("Logo file not found");
        }
        this.add(startNewGame);
        this.add(loadGame);
    }

    // EFFECTS: initialises a new game or loads a previous game depending on which button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startNewGame) {
            gui.initializeNewGame();
        } else if (e.getSource() == loadGame) {
            gui.initializeOldGame();
        }
    }
}

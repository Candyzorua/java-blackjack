package ui.gameui.panels;

import ui.gameui.HandTable;
import model.Player;

import javax.swing.*;
import java.awt.*;

public abstract class PlayerPanel extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    protected JLabel statusLabel;
    protected final Player player;
    private HandTable handTable;
    private boolean active;


    public PlayerPanel(Player player) {
        this.player = player;
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.addLabels();
        this.addHandTable();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackgroundColor();
    }

    public abstract void addLabels();

    private void addHandTable() {
        handTable = new HandTable(player.getHand().getContents());
        this.add(handTable);
    }

    private void setBackgroundColor() {
        if (active) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(Color.GRAY);
        }
    }

    // getters
    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public HandTable getHandTable() {
        return handTable;
    }

    // setters
    public void setActive(Boolean active) {
        this.active = active;
        setBackgroundColor();
    }
}

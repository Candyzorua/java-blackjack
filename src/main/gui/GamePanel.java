package gui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JFrame {
    private CardLayout crd;
    JPanel configMenu;

    public GamePanel() {
        super("Java Blackjack");
        this.setSize(300, 400);
        this.setVisible(true);
    }
}

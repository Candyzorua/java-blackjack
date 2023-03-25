package gui;

import cli.GameLoader;
import cli.GameSaver;
import gui.closingpanel.ClosingPanel;
import gui.configmenu.PlayerConfigMenu;
import gui.gameui.RoundUI;
import gui.openingpanel.OpeningPanel;
import gui.wagertaker.WagerTaker;
import model.BGame;
import model.Round;

import javax.swing.*;
import java.awt.*;

/**
 * Coordinates the GUI.
 */

public class BGameUI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private final CardLayout cardLayout = new CardLayout();
    private BGame bg;
    private final GameLoader gameLoader;
    private final GameSaver gameSaver;

    public BGameUI() {
        gameLoader = new GameLoader();
        gameSaver = new GameSaver();
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(cardLayout);
        this.add("OpeningPanel", new OpeningPanel(this));
        this.setVisible(true);
    }

    public void initializeNewGame() {
        bg = gameLoader.loadDefaultPlayers();
        initializeConfigMenu();
    }

    public void initializeOldGame() {
        bg = gameLoader.loadGame();
        initializeConfigMenu();
    }

    public void initializeConfigMenu() {
        PlayerConfigMenu playerConfigMenu = new PlayerConfigMenu(bg, this);
        this.add("PlayerConfigMenu", playerConfigMenu);
        cardLayout.show(this, "PlayerConfigMenu");
    }

    public void initializeWagerTaker() {
        WagerTaker wagerTaker = new WagerTaker(bg, this);
        this.add("WagerTaker", wagerTaker);
        cardLayout.show(this, "WagerTaker");
    }

    public void initializeRoundUI(Round round) {
        RoundUI roundUI = new RoundUI(round, this);
        this.add("RoundUI", roundUI);
        cardLayout.show(this, "RoundUI");
        roundUI.nextMove();
    }

    public void initializeClosingPanel() {
        ClosingPanel closingPanel = new ClosingPanel(bg, this);
        this.add("ClosingPanel", closingPanel);
        cardLayout.show(this, "ClosingPanel");
    }

    public void saveGame() {
        gameSaver.saveGame(bg);
    }

    public void exitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        BGameUI gui = new BGameUI();
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.add(gui);
        frame.setVisible(true);
    }
}

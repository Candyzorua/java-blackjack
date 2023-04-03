package ui;

import model.Event;
import model.EventLog;
import ui.closingpanel.ClosingPanel;
import ui.configmenu.PlayerConfigMenu;
import ui.gameui.RoundUI;
import ui.openingpanel.OpeningPanel;
import ui.persistence.GameLoader;
import ui.persistence.GameSaver;
import ui.wagertaker.WagerTaker;
import model.BGame;
import model.Round;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that coordinates the GUI
 */

public class BGameUI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private final CardLayout cardLayout = new CardLayout();
    private BGame bg;
    private final GameLoader gameLoader;
    private final GameSaver gameSaver;
    private final JFrame frame;

    // EFFECTS: constructs a new BGameUI showing the opening panel
    public BGameUI() {
        gameLoader = new GameLoader();
        gameSaver = new GameSaver();

        frame = new JFrame();
        frame.setSize(300, 300);

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(cardLayout);
        this.add("OpeningPanel", new OpeningPanel(this));
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads a new game and swaps to the player configuration menu
    public void initializeNewGame() {
        bg = gameLoader.loadDefaultPlayers();
        initializeConfigMenu();
    }

    // MODIFIES: this
    // EFFECTS: loads a previously saved game and swaps to the player configuration menu
    public void initializeOldGame() {
        bg = gameLoader.loadGame();
        initializeConfigMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays the player configuration menu
    public void initializeConfigMenu() {
        PlayerConfigMenu playerConfigMenu = new PlayerConfigMenu(bg, this);
        this.add("PlayerConfigMenu", playerConfigMenu);
        cardLayout.show(this, "PlayerConfigMenu");
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays the screen for wager taking
    public void initializeWagerTaker() {
        WagerTaker wagerTaker = new WagerTaker(bg, this);
        this.add("WagerTaker", wagerTaker);
        cardLayout.show(this, "WagerTaker");
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays the screen for a round of Blackjack
    //          starts the round of Blackjack
    public void initializeRoundUI(Round round) {
        RoundUI roundUI = new RoundUI(round, this);
        this.add("RoundUI", roundUI);
        cardLayout.show(this, "RoundUI");
        roundUI.nextMove();
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays the closing panel
    public void initializeClosingPanel() {
        ClosingPanel closingPanel = new ClosingPanel(bg, this);
        this.add("ClosingPanel", closingPanel);
        cardLayout.show(this, "ClosingPanel");
    }

    // EFFECTS: saves the game into a local .json file
    public void saveGame() {
        gameSaver.saveGame(bg);
    }

    // EFFECTS: exits the program
    public void exitGame() {
        logEvents();
        frame.dispose();
    }

    // EFFECTS: prints event log to console
    public void logEvents() {
        EventLog theLog = EventLog.getInstance();
        for (Event e: theLog) {
            System.out.println(e.toString());
        }
    }

    // getters
    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        BGameUI gui = new BGameUI();
        JFrame frame = gui.getFrame();
        frame.add(gui);
        frame.setVisible(true);
    }
}

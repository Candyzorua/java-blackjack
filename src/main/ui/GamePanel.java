package ui;

import model.*;

import java.io.FileNotFoundException;

import static ui.InputTaker.takeInput;

/**
 * Main CLI for this program
 */

public class GamePanel {
    private final BGame bg;
    private final GameLoader gameLoader;
    private final GameSaver gameSaver;
    private final RoundManager roundManager;
    private final PlayerConfigurator playerConfigurator;

    // EFFECTS: constructs GamePanel and runs application
    public GamePanel() throws FileNotFoundException {
        bg = new BGame();
        gameLoader = new GameLoader();
        gameSaver = new GameSaver();
        roundManager = new RoundManager(bg);
        playerConfigurator = new PlayerConfigurator(bg);
        runGamePanel();
    }

    // MODIFIES: this
    // EFFECTS: runs application
    private void runGamePanel() {
        boolean continueGame = true;
        System.out.println("Welcome to Java Blackjack!");
        shouldLoadGame();
        while (continueGame) {
            playerConfigurator.configure();
            roundManager.startNewRound();
            continueGame = shouldRoundContinue();
        }
        shouldSaveGame();
    }

    // MODIFIES: this
    // EFFECTS: takes user input on whether previous game should be loaded and loads game if answer is yes
    private void shouldLoadGame() {
        System.out.println("Would you like to load a game?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            gameLoader.loadGame(bg);
        } else {
            System.out.println("Starting on a clean slate!");
            gameLoader.loadDefaultPlayers(bg);
        }
    }

    // MODIFIES: this
    // EFFECTS: takes user input on whether the game should be saved and saves game if answer is yes
    private void shouldSaveGame() {
        System.out.println("Would you like to save this game?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            gameSaver.saveGame(bg);
        }
        System.out.println("Thanks for playing!");
    }

    // EFFECTS: takes and returns user input on whether the game should continue
    private boolean shouldRoundContinue() {
        System.out.println("Would you like to play another round?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        return "y".equals(continueRoundUserInput);
    }
}

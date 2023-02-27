package ui;

import exceptions.NotEnoughPlayers;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main UI for this program
 */

public class GamePanel {
    private static final String JSON_STORE = "./data/game.json";
    private BGame bg;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs GamePanel and runs application
    public GamePanel() throws FileNotFoundException {
        bg = new BGame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGamePanel();
    }

    private void runGamePanel() {
        boolean continueGame = true;
        System.out.println("Welcome to Java Blackjack!");
        shouldLoadGame();
        while (continueGame) {
            configure(bg);
            startNewRound(bg);
            continueGame = shouldRoundContinue();
        }
        shouldSaveGame();
    }

    // EFFECTS: takes user input on whether previous game should be loaded and loads game if answer is yes
    private void shouldLoadGame() {
        System.out.println("Would you like to load a game?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            loadGame();
        } else {
            System.out.println("Starting on a clean slate!");
            loadDefaultPlayers(bg);
        }
    }

    // EFFECTS: takes user input on whether the game should be saved and saves game if answer is yes
    private void shouldSaveGame() {
        System.out.println("Would you like to save this game?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            saveGame();
        }
        System.out.println("Thanks for playing!");
    }

    // EFFECTS: takes and returns user input on whether the game should continue
    private boolean shouldRoundContinue() {
        System.out.println("Would you like to play another round?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        return "y".equals(continueRoundUserInput);
    }

    // MODIFIES: BGame
    // EFFECTS: starts a new round of blackjack
    private void startNewRound(BGame g1) {
        System.out.println("Starting a new round! Collecting wagers... \n");
        Round r1 = g1.startRound();
        handleRound(r1);
    }

    // MODIFIES: g1
    // EFFECTS: enters game configuration menu
    private void configure(BGame g1) {
        System.out.println("Entering configuration menu... \n");
        configureGame(g1);
        System.out.println("Exiting configuration menu... \n");
    }

    // MODIFIES: g1
    // EFFECTS: handles actions related to game configuration
    private void configureGame(BGame g1) {
        boolean keepConfiguring = true;
        while (keepConfiguring) {
            displayGameSummary(g1);
            String userInput = takeInput("Press 'a' to add a player, 'r' to remove a player, "
                    + "'d' to set another player as the dealer, and 'c' to continue");
            switch (userInput) {
                case "a":
                    addPlayer(g1);
                    break;
                case "r":
                    removePlayer(g1);
                    break;
                case "d":
                    setNewDealer(g1);
                    break;
                case "c":
                    keepConfiguring = false;
                    break;
            }
        }
    }

    // MODIFIES: g1
    // EFFECTS: tries to add a player to the game
    //          fails if player with the same name is already in the game
    //          prints appropriate confirmation messages (fail or succeed)
    private void addPlayer(BGame g1) {
        String newPlayerName = takeInput("New player name:");
        boolean result = g1.addPlayer(new Player(newPlayerName, 0));
        if (result) {
            System.out.println("New player " + newPlayerName + " added.");
        } else {
            System.out.println("Sorry, that player could not be added.");
        }
    }

    // MODIFIES: g1
    // EFFECTS: tries to remove a player from the game
    //          fails if the number of players <= g1.MIN_PLAYERS
    //          prints appropriate confirmation messages (fail or succeed)
    private void removePlayer(BGame g1) {
        String playerName = takeInput("Please select a player to remove");
        Player playerToRemove = selectPlayer(g1, playerName);
        boolean result = g1.removePlayer(playerToRemove);
        if (result) {
            System.out.println("Player " + playerToRemove.getName() + " removed.");
        } else {
            System.out.println("Sorry, that player could not be removed.");
        }
    }

    // MODIFIES: g1
    // EFFECTS: lets the user set a new regular player as the dealer by name, and the old dealer becomes
    //          a regular player
    //          fails if the player gives an invalid name
    //          prints appropriate confirmation messages
    private void setNewDealer(BGame g1) {
        String dealerName = takeInput("Please select a player to set as the dealer");
        Player newDealer = selectPlayer(g1, dealerName);
        boolean result = g1.setPlayerAsDealer(newDealer);
        if (result) {
            System.out.println("New dealer " + dealerName + " set.");
        } else {
            System.out.println("Sorry, that player could not be set as the dealer.");
        }
    }

    // EFFECTS: returns a regular player with the given name from the list of regular players
    //          returns null if no player with the given name from the list of regular players
    private Player selectPlayer(BGame g1, String playerName) {
        for (Player p: g1.getRegularPlayers()) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: displays a summary of the game
    private void displayGameSummary(BGame g1) {
        System.out.println("Displaying game summary...");
        System.out.println("--- Player Summary ---");
        for (Player p: g1.getRegularPlayers()) {
            displayPlayerSummaryGame(p);
        }
        System.out.println("--- Dealer Summary ---");
        displayPlayerSummaryGame(g1.getDealer());
    }

    // EFFECTS: displays a game summary for a single player
    private void displayPlayerSummaryGame(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    // MODIFIES: r1
    // EFFECTS: executes a single round of blackjack
    private void handleRound(Round r1) {
        takeWagers(r1);
        System.out.println("\nTime for some very serious business!\n");
        takeStatuses(r1);
        System.out.println("Now dealing with payouts... \n");
        r1.handlePayouts();
        displayRoundSummary(r1);
    }

    // MODIFIES: r1
    // EFFECTS: takes wagers of all players
    private void takeWagers(Round r1) {
        for (Player p : r1.getRegularPlayers()) {
            try {
                int wager = Integer.parseInt(takeInput("What is " + p.getName() + "'s wager?"));
                p.setWager(wager);
            } catch (IllegalArgumentException e) {
                System.out.println("That's not a valid wager! Setting wager as 1...");
                p.setWager(1);
            }
        }
    }

    // MODIFIES: p, r1
    // EFFECTS: take round status of a single player
    private void takeStatus(Player p, Round r1) {
        System.out.println("It's " + p.getName() + "'s turn.");
        if (p.getStatus() == RoundStatus.BLACKJACK) {
            System.out.println("Congratulations sucker, you got blackjack! \n");
        }

        while (p.getStatus() == RoundStatus.PENDING) {
            displayPlayerSummaryRound(p);
            String choice = takeInput("Enter 'h' to hit and 's' to stand.");
            switch (choice) {
                case "s":
                    System.out.println("Good choice... maybe. \n");
                    p.setStatus(RoundStatus.STAND);
                    break;
                case "h":
                    System.out.println("Let's see what your luck is like today...");
                    RoundStatus newPlayerStatus = r1.letPlayerHit(p);
                    if (newPlayerStatus == RoundStatus.BUST) {
                        System.out.println("Boom! You busted. \n");
                    }
                    break;
            }
        }
    }

    // MODIFIES: r1
    // EFFECTS: take round status of all players
    private void takeStatuses(Round r1) {
        for (Player p : r1.getRegularPlayers()) {
            takeStatus(p, r1);
        }
        System.out.println("Time for the dealer...\n");
        Player roundDealer = r1.getDealer();
        takeStatus(roundDealer, r1);
    }


    // EFFECTS: displays statuses, total scores, and hands of all players
    public void displayRoundSummary(Round r1) {
        System.out.println("Displaying round summary...");
        System.out.println("Round Number: " + r1.getRoundNumber());
        System.out.println("--- Player Summary ---");
        for (Player p : r1.getRegularPlayers()) {
            displayPlayerSummaryRound(p);
        }
        System.out.println("--- Dealer Summary ---");
        displayPlayerSummaryRound(r1.getDealer());
    }

    // EFFECTS: displays the round summary for a single player
    public void displayPlayerSummaryRound(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Current wager: " + p.getWager());
        System.out.println("Current hand: " + p.getHandAsString());
        System.out.println("Round result: " + p.getStatus());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    // MODIFIES: g1
    // EFFECTS: loads the default players into the game
    private void loadDefaultPlayers(BGame g1) {
        Player p1 = new Player("Jin", 0);
        Player p2 = new Player("Mikayla", 0);
        Player p3 = new Player("Victor", 0);
        Player p4 = new Player("Leona", 0);
        Player p5 = new Player("Amy", 0);
        g1.addPlayer(p1);
        g1.addPlayer(p2);
        g1.addPlayer(p3);
        g1.addPlayer(p4);
        g1.addPlayer(p5);
        g1.setPlayerAsDealer(p5);
    }

    // EFFECTS: takes and returns user input with given prompt
    private String takeInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // EFFECTS: saves the game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(bg);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file and automatically sets the last player as the dealer
    private void loadGame() {
        try {
            bg = jsonReader.read();
            if (bg.getNumPlayers() < BGame.MIN_PLAYERS) {
                throw new NotEnoughPlayers();
            }
            System.out.println("Loaded game from " + JSON_STORE + ".");
            Player lastPlayer = bg.getRegularPlayers().get(bg.getNumPlayers() - 1);
            bg.setPlayerAsDealer(lastPlayer);
            System.out.println("The last player has been set as the dealer by default.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + ".");
        } catch (NotEnoughPlayers nep) {
            System.out.println("Loaded game does not have enough players.");
            System.out.println("Loading default players instead...");
        }
    }
}

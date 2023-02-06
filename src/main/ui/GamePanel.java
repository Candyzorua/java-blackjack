package ui;

import model.*;

import java.util.Scanner;

public class GamePanel {

    public static void main(String[] args) {
        boolean continueGame = true;
        BGame g1 = new BGame();
        loadDefaultPlayers(g1);
        while (continueGame) {
            configure(g1);
            startNewRound(g1);
            continueGame = shouldRoundContinue();
        }
    }

    // EFFECTS: takes user input on whether the game should continue
    private static boolean shouldRoundContinue() {
        System.out.println("Would you like to play another round?");
        String continueRoundUserInput = takeInput("Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            return true;
        }
        System.out.println("Thanks for playing!");
        return false;
    }

    // MODIFIES: BGame
    // EFFECTS: starts a new round of blackjack
    private static void startNewRound(BGame g1) {
        System.out.println("Starting a new round! Collecting wagers... \n");
        Round r1 = g1.startRound();
        handleRound(r1);
    }

    // EFFECTS: enters configuration menu
    private static void configure(BGame g1) {
        System.out.println("Entering configuration menu... \n");
        configureGame(g1);
        System.out.println("Exiting configuration menu... \n");
    }

    // EFFECTS: handles actions related to game configuration
    private static void configureGame(BGame g1) {
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
    // EFFECTS: tries to add a player to the game and prints appropriate confirmation messages
    private static void addPlayer(BGame g1) {
        String newPlayerName = takeInput("New player name:");
        boolean result = g1.addPlayer(new RegularPlayer(newPlayerName, 0));
        if (result) {
            System.out.println("New player " + newPlayerName + " added.");
        } else {
            System.out.println("Sorry, that player could not be added.");
        }
    }

    // MODIFIES: g1
    // EFFECTS: tries to remove a player from the game and prints appropriate confirmation messages
    private static void removePlayer(BGame g1) {
        String playerName = takeInput("Please select a player to remove");
        RegularPlayer playerToRemove = selectPlayer(g1, playerName);
        boolean result = g1.removePlayer(playerToRemove);
        if (result) {
            System.out.println("Player " + playerToRemove.getName() + " removed.");
        } else {
            System.out.println("Sorry, that player could not be removed.");
        }
    }

    private static void setNewDealer(BGame g1) {
        String dealerName = takeInput("Please select a player to set as the dealer");
        RegularPlayer newDealer = selectPlayer(g1, dealerName);
        boolean result = g1.setPlayerAsDealer(newDealer);
        if (result) {
            System.out.println("New dealer " + dealerName + " set.");
        } else {
            System.out.println("Sorry, that player could not be set as the dealer.");
        }
    }

    // EFFECTS: returns a regular player with the given name from the list of regular players
    //          returns null if no player with the given name from the list of regular players
    private static RegularPlayer selectPlayer(BGame g1, String playerName) {
        for (RegularPlayer p: g1.getRegularPlayers()) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: displays a summary of the game
    private static void displayGameSummary(BGame g1) {
        System.out.println("Displaying game summary...");
        System.out.println("--- Player Summary ---");
        for (RegularPlayer p: g1.getRegularPlayers()) {
            displayPlayerSummaryGame(p);
        }
        System.out.println("--- Dealer Summary ---");
        displayPlayerSummaryGame(g1.getDealer());
    }

    // EFFECTS: displays a game summary for a single player
    private static void displayPlayerSummaryGame(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    // EFFECTS: executes a single round of blackjack
    private static void handleRound(Round r1) {
        takeWagers(r1);
        System.out.println("\nTime for some very serious business!\n");
        takeStatuses(r1);
        System.out.println("Now dealing with payouts... \n");
        r1.handlePayouts();
        displayRoundSummary(r1);
    }

    // EFFECTS: takes wagers of all players
    private static void takeWagers(Round r1) {
        for (RegularPlayer p : r1.getRegularPlayers()) {
            int wager = Integer.parseInt(takeInput("What is " + p.getName() + "'s wager?"));
            p.setWager(wager);
        }
    }

    // EFFECTS: take round status of a single player
    private static void takeStatus(Player p, Round r1) {
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

    // EFFECTS: take round status of all players
    private static void takeStatuses(Round r1) {
        for (RegularPlayer p : r1.getRegularPlayers()) {
            takeStatus(p, r1);
        }
        System.out.println("Time for the dealer...\n");
        Dealer roundDealer = r1.getDealer();
        takeStatus(roundDealer, r1);
    }


    // EFFECTS: displays statuses, total scores, and hands of all players
    public static void displayRoundSummary(Round r1) {
        System.out.println("Displaying round summary...");
        System.out.println("Round Number: " + r1.getRoundNumber());
        System.out.println("--- Player Summary ---");
        for (RegularPlayer p : r1.getRegularPlayers()) {
            displayPlayerSummaryRound(p);
        }
        System.out.println("--- Dealer Summary ---");
        displayDealerSummaryRound(r1.getDealer());
    }

    public static void displayPlayerSummaryRound(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Current wager: " + p.getWager());
        System.out.println("Current hand: " + p.getHandAsString());
        System.out.println("Round result: " + p.getStatus());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    public static void displayDealerSummaryRound(Player p) {
        System.out.println("Dealer name: " + p.getName());
        System.out.println("Current hand: " + p.getHandAsString());
        System.out.println("Round result: " + p.getStatus());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    // EFFECTS: loads the default players into the game
    private static void loadDefaultPlayers(BGame g1) {
        RegularPlayer p1 = new RegularPlayer("Jin", 0);
        RegularPlayer p2 = new RegularPlayer("Mikayla", 0);
        RegularPlayer p3 = new RegularPlayer("Victor", 0);
        RegularPlayer p4 = new RegularPlayer("Leona", 0);
        RegularPlayer p5 = new RegularPlayer("Amy", 0);
        g1.addPlayer(p1);
        g1.addPlayer(p2);
        g1.addPlayer(p3);
        g1.addPlayer(p4);
        g1.addPlayer(p5);
        g1.setPlayerAsDealer(p5);
    }

    // EFFECTS: takes input with given prompt
    private static String takeInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}

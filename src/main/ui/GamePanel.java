package ui;

import model.BGame;
import model.Player;
import model.Round;
import model.RoundStatus;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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
        String continueRoundUserInput = takeInput("Would you like to play another round?"
                + "Type 'y' for yes, and any other key for no.");
        if ("y".equals(continueRoundUserInput)) {
            return true;
        }
        System.out.println("Thanks for playing!");
        return false;
    }

    // MODIFIES: BGame
    // EFFECTS: starts a new round of blackjack
    private static void startNewRound(BGame g1) {
        System.out.println("Starting a new round!");
        Round r1 = g1.startRound();
        handleRound(r1);
    }

    // EFFECTS: enters configuration menu
    private static void configure(BGame g1) {
        System.out.println("Entering configuration menu...");
        configureGame(g1);
        System.out.println("Exiting configuration menu...");
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
        boolean result = g1.addPlayer(new Player(newPlayerName, 0));
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
        Player playerToRemove = selectPlayer(g1, playerName);
        boolean result = g1.removePlayer(playerToRemove);
        if (result) {
            System.out.println("Player " + playerToRemove.getName() + " removed.");
        } else {
            System.out.println("Sorry, that player could not be removed.");
        }
    }

    private static void setNewDealer(BGame g1) {
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
    private static Player selectPlayer(BGame g1, String playerName) {
        for (Player p: g1.getRegularPlayerList()) {
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
        for (Player p: g1.getRegularPlayerList()) {
            int playerNum = g1.getRegularPlayerList().indexOf(p) + 1;
            System.out.println("Player " +  playerNum);
            System.out.println("Player name: " + p.getName());
            System.out.println("Total score: " + p.getScore());
            System.out.println(" ");
        }
        System.out.println("--- Dealer Summary ---");
        System.out.println("Dealer name: " + g1.getDealer().getName());
        System.out.println("Dealer score: " + g1.getDealer().getScore());
    }

    // EFFECTS: executes a single round of blackjack
    private static void handleRound(Round r1) {
        takeWagers(r1);
        System.out.println("Time to find out if these players stand, bust or get a blackjack!");
        System.out.println("Enter 'b' for bust, 's' for stand, and 'bj' for blackjack");
        takeStatuses(r1);
        System.out.println("Now dealing with payouts...");
        r1.handlePayouts();
        displayRoundSummary(r1);
    }

    // EFFECTS: takes wagers of all players
    private static void takeWagers(Round r1) {
        for (Player p : r1.getRegularPlayerList()) {
            int wager = Integer.parseInt(takeInput("What is " + p.getName() + "'s wager? "));
            p.setWager(wager);
        }
    }

    // EFFECTS: take round status of a single player
    private static void takeStatus(Player p) {
        String status = takeInput("What is " + p.getName() + "'s status? ");
        switch (status) {
            case "b":
                p.setStatus(RoundStatus.BUST);
                break;
            case "s":
                p.setStatus(RoundStatus.STAND);
                int hand = Integer.parseInt(takeInput("What is " + p.getName() + "'s hand? "));
                p.setHand(hand);
                break;
            case "bj":
                p.setStatus(RoundStatus.BLACKJACK);
                System.out.println("Congratulations!");
                break;
        }
    }

    // EFFECTS: take round status of all players
    private static void takeStatuses(Round r1) {
        for (Player p : r1.getRegularPlayerList()) {
            displayRoundSummary(r1);
            takeStatus(p);
        }
        System.out.println("Time for the dealer...");
        Player roundDealer = r1.getDealer();
        displayRoundSummary(r1);
        takeStatus(roundDealer);
    }


    // EFFECTS: displays statuses, total scores, and hands of all players
    public static void displayRoundSummary(Round r1) {
        System.out.println("Displaying round summary...");
        System.out.println("Round Number: " + r1.getRoundNumber());
        System.out.println("--- Player Summary ---");
        for (Player p : r1.getRegularPlayerList()) {
            int playerNum = r1.getRegularPlayerList().indexOf(p) + 1;
            System.out.println("Player " +  playerNum);
            System.out.println("Player name: " + p.getName());
            System.out.println("Round status: " + p.getStatus());
            System.out.println("Current wager: " + p.getWager());
            System.out.println("Current hand: " + p.getHand());
            System.out.println("Total score: " + p.getScore());
            System.out.println(" ");
        }
        System.out.println("--- Dealer Summary ---");
        System.out.println("Dealer name: " + r1.getDealer().getName());
        System.out.println("Round status: " + r1.getDealer().getStatus());
        System.out.println("Current hand: " + r1.getDealer().getHand());
        System.out.println("Total score: " + r1.getDealer().getScore());
        System.out.println(" ");
    }

    // EFFECTS: loads the default players into the game
    private static void loadDefaultPlayers(BGame g1) {
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

    // EFFECTS: takes input with given prompt
    private static String takeInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}

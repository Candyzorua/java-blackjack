package ui;

import model.BGame;
import model.Player;
import model.Round;

/**
 * Utility class for summary printing in UI
 */

public final class SummaryPrinter {
    private SummaryPrinter(){}

    // EFFECTS: displays statuses, total scores, and hands of all players
    public static void displayRoundSummary(Round r1) {
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
    public static void displayPlayerSummaryRound(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Current wager: " + p.getWager());
        System.out.println("Current hand: " + p.getHandAsString());
        System.out.println("Round result: " + p.getStatus());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }

    // EFFECTS: displays a summary of the game
    public static void displayGameSummary(BGame g1) {
        System.out.println("Displaying game summary...");
        System.out.println("--- Player Summary ---");
        for (Player p : g1.getRegularPlayers()) {
            displayPlayerSummaryGame(p);
        }
        System.out.println("--- Dealer Summary ---");
        displayPlayerSummaryGame(g1.getDealer());
    }

    // EFFECTS: displays a game summary for a single player
    public static void displayPlayerSummaryGame(Player p) {
        System.out.println("Player name: " + p.getName());
        System.out.println("Total score: " + p.getScore());
        System.out.println(" ");
    }
}

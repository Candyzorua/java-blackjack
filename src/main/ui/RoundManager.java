package ui;

import model.BGame;
import model.Player;
import model.Round;
import model.RoundStatus;

import static ui.InputTaker.takeInput;
import static ui.SummaryPrinter.displayPlayerSummaryRound;
import static ui.SummaryPrinter.displayRoundSummary;

public class RoundManager {
    private Round round;
    private final BGame bg;


    public RoundManager(BGame bg) {
        this.bg = bg;
        round = null;
    }

    // MODIFIES: this
    // EFFECTS: starts a new round of blackjack
    public void startNewRound() {
        System.out.println("Starting a new round! Collecting wagers... \n");
        round = bg.startRound();
        handleRound();
    }

    // MODIFIES: this
    // EFFECTS: executes a single round of blackjack
    private void handleRound() {
        takeWagers();
        System.out.println("\nTime for some very serious business!\n");
        takeStatuses();
        System.out.println("Now dealing with payouts... \n");
        round.handlePayouts();
        displayRoundSummary(round);
    }

    // MODIFIES: this
    // EFFECTS: takes wagers of all players
    //          if an invalid wager is given, the default wager of 1 is set for that player
    private void takeWagers() {
        for (Player p : round.getRegularPlayers()) {
            try {
                int wager = Integer.parseInt(takeInput("What is " + p.getName() + "'s wager?"));
                p.setWager(wager);
            } catch (IllegalArgumentException e) {
                System.out.println("That's not a valid wager! Setting wager as 1...");
                p.setWager(1);
            }
        }
    }

    // MODIFIES: p, this
    // EFFECTS: take round status of a single player
    private void takeStatus(Player p) {
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
                    RoundStatus newPlayerStatus = round.letPlayerHit(p);
                    if (newPlayerStatus == RoundStatus.BUST) {
                        System.out.println("Boom! You busted. \n");
                    }
                    break;
            }
        }
    }

    // MODIFIES: r1
    // EFFECTS: take round status of all players
    private void takeStatuses() {
        for (Player p : round.getRegularPlayers()) {
            takeStatus(p);
        }
        System.out.println("Time for the dealer...\n");
        Player roundDealer = round.getDealer();
        takeStatus(roundDealer);
    }
}

package ui;

import model.BGame;
import model.Player;
import model.Round;
import model.RoundStatus;

import java.util.Scanner;

public class GamePanel {

    public static void main(String[] args) {
        BGame g1 = new BGame();
        loadDefaultPlayers(g1);

        Round r1 = g1.startRound();
        System.out.println("Starting a new round!");
        handleRound(r1);
    }

    private static String takeInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    private static void configurePlayers(BGame g1) {
        // stub
    }

    private static void handleRound(Round r1) {
        takeWagers(r1);
        System.out.println("Time to find out if these players stand, bust or get a blackjack!");
        System.out.println("Enter 'b' for bust, 's' for stand, and 'bj' for blackjack");
        takeStatuses(r1);
        System.out.println("Now dealing with payouts...");
        r1.handlePayouts();
        r1.displaySummary();
    }

    private static void takeWagers(Round r1) {
        for (Player p: r1.getRegularPlayerList()) {
            int wager = Integer.parseInt(takeInput("What is " + p.getName() +  "'s wager? "));
            p.setWager(wager);
        }
    }

    private static void takeStatuses(Round r1) {
        for (Player p: r1.getRegularPlayerList()) {
            r1.displaySummary();
            String status = takeInput("What is " + p.getName() +  "'s status? ");
            switch (status) {
                case "b":
                    p.setStatus(RoundStatus.BUST);
                    break;
                case "s":
                    p.setStatus(RoundStatus.STAND);
                    int hand = Integer.parseInt(takeInput("What is " + p.getName() +  "'s hand? "));
                    p.setHand(hand);
                    break;
                case "bj":
                    p.setStatus(RoundStatus.BLACKJACK);
                    System.out.println("Congratulations!");
                    break;
            }
        }
        System.out.println("Time for the dealer...");
        Player roundDealer = r1.getDealer();
        String status = takeInput("What is " + roundDealer.getName() +  "'s status? ");
        switch (status) {
            case "b":
                roundDealer.setStatus(RoundStatus.BUST);
                break;
            case "s":
                roundDealer.setStatus(RoundStatus.STAND);
                int hand = Integer.parseInt(takeInput("What is " + roundDealer.getName() +  "'s hand? "));
                roundDealer.setHand(hand);
                break;
            case "bj":
                roundDealer.setStatus(RoundStatus.BLACKJACK);
                System.out.println("Wow!");
                break;
        }
    }

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
}

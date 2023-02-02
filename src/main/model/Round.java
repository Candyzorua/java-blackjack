package model;

import java.util.List;

import static model.RoundStatus.BLACKJACK;
import static model.RoundStatus.STAND;

public class Round {
    private final int roundNumber;
    private final List<Player> regularPlayerList;
    private final Player dealer;

    // REQUIRES: regularPlayerList must have at least 1 element
    // EFFECTS: constructs a new round with given players and dealer
    public Round(int roundNumber, List<Player> regularPlayerList, Player dealer) {
        this.roundNumber = roundNumber;
        this.regularPlayerList = regularPlayerList;
        this.dealer = dealer;
    }

    // MODIFIES: this
    // EFFECTS: records the given wager of a given player
    public void recordWager(Player player, int wager) {
        player.setWager(wager);
    }

    // MODIFIES: this
    // EFFECTS: updates player round status
    public void recordStatus(Player player, RoundStatus status) {
        player.setStatus(status);
    }

    // MODIFIES: this
    // EFFECTS: record the player's hand
    public void recordHand(Player player, int hand) {
        player.setHand(hand);
    }

    // MODIFIES: this
    // EFFECTS: handle payouts according to player statuses, player hands and blackjack rules
    public void handlePayouts() {
        if (dealer.getStatus() == BLACKJACK) {
            for (Player p : regularPlayerList) {
                if (p.getStatus() != BLACKJACK) {
                    p.deductScore(p.getWager() * 2);
                    dealer.addScore(p.getWager() * 2);
                }
            }
        } else {
            for (Player p : regularPlayerList) {
                switch (p.getStatus()) {
                    case BUST:
                        p.deductScore(p.getWager());
                        dealer.addScore(p.getWager());
                        break;
                    case STAND:
                        if (p.getHand() > dealer.getHand()) {
                            p.addScore(p.getWager());
                            dealer.deductScore(p.getWager());
                        } else if (p.getHand() < dealer.getHand()) {
                            dealer.addScore(p.getWager());
                            p.deductScore(p.getWager());
                        }
                        break;
                    case BLACKJACK:
                        p.addScore(p.getWager() * 2);
                        dealer.deductScore(p.getWager() * 2);
                        break;
                }
            }
        }
    }

    // EFFECTS: displays statuses, total scores, and hands of all players
    public void displaySummary() {
        System.out.println("Round Number: " + roundNumber);
        System.out.println("--- Player Summary ---");
        for (Player p : regularPlayerList) {
            System.out.println("Player name: " + p.getName());
            System.out.println("Round status: " + p.getStatus());
            System.out.println("Current wager: " + p.getWager());
            System.out.println("Current hand: " + p.getHand());
            System.out.println("Total score: " + p.getScore());
            System.out.println(" ");
        }
    }

    // getters
    public int getRoundNumber() {
        return roundNumber;
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getRegularPlayerList() {
        return regularPlayerList;
    }
}

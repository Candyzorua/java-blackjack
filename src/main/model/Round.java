package model;

import java.util.List;

import static model.RoundStatus.BLACKJACK;

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
    // EFFECTS: handle payouts when dealer has blackjack
    public void handleDealerBlackjack() {
        for (Player p : regularPlayerList) {
            if (p.getStatus() != BLACKJACK) {
                p.deductScore(p.getWager() * 2);
                dealer.addScore(p.getWager() * 2);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handle payouts according to player statuses, player hands and blackjack rules
    public void handlePayouts() {
        if (dealer.getStatus() == BLACKJACK) {
            handleDealerBlackjack();
        } else {
            for (Player p : regularPlayerList) {
                switch (p.getStatus()) {
                    case BUST:
                        handleBust(p);
                        break;
                    case STAND:
                        handleStand(p);
                        break;
                    case BLACKJACK:
                        handleBlackjack(p);
                        break;
                }
            }
        }
    }

    // EFFECTS: handle payout when a single player busts
    public void handleBust(Player p) {
        p.deductScore(p.getWager());
        dealer.addScore(p.getWager());
    }

    // EFFECTS: handle payout when a single player stands
    public void handleStand(Player p) {
        if (p.getHand() > dealer.getHand()) {
            p.addScore(p.getWager());
            dealer.deductScore(p.getWager());
        } else if (p.getHand() < dealer.getHand()) {
            dealer.addScore(p.getWager());
            p.deductScore(p.getWager());
        }
    }

    // EFFECTS: handle payout when a single player gets blackjack
    public void handleBlackjack(Player p) {
        p.addScore(p.getWager() * 2);
        dealer.deductScore(p.getWager() * 2);
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

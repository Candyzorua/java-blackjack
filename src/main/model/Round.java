package model;

import java.util.List;

import static model.RoundStatus.BLACKJACK;
import static model.RoundStatus.BUST;

public class Round implements Playable {

    private final int roundNumber;
    private final List<RegularPlayer> regularPlayerList;
    private final Player dealer;
    private final CardDeck cd;

    // REQUIRES: regularPlayerList has at least as many players as the min. number of players
    //           specified by the game
    // EFFECTS: constructs a new round with given players, given dealer, and fresh deck of cards, which is dealt
    public Round(int roundNumber, List<RegularPlayer> regularPlayerList, Player dealer) {
        this.roundNumber = roundNumber;
        this.regularPlayerList = regularPlayerList;
        this.dealer = dealer;
        cd = new CardDeck();
        dealCardsToAllPlayers();
    }

    // REQUIRES: all players' hands must be empty
    // MODIFIES: this
    // EFFECTS: record all players' initial hand and updates their roundStatus if they get blackjack
    public void dealCardsToAllPlayers() {
        for (Player p: regularPlayerList) {
            p.dealInitialCards(cd);
            if (p.getHandSize() == 21 | p.getHandSize() == 22) {
                p.setStatus(BLACKJACK);
            }
        }
        dealer.dealInitialCards(cd);
        if (dealer.getHandSize() == 21 | dealer.getHandSize() == 22) {
            dealer.setStatus(BLACKJACK);
        }
    }

    // MODIFIES: this
    // EFFECTS: the player hits, adjusts player status accordingly, returns new player status
    public RoundStatus letPlayerHit(Player p) {
        p.drawCard(cd);
        if (p.getHandSize() > 21) {
            p.setStatus(RoundStatus.BUST);
        }
        return p.getStatus();
    }

    // MODIFIES: this
    // EFFECTS: handle payouts when dealer has blackjack
    private void handleDealerBlackjack() {
        for (RegularPlayer p : regularPlayerList) {
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
            for (RegularPlayer p : regularPlayerList) {
                switch (p.getStatus()) {
                    case BUST:
                        handleBust(p);
                        break;
                    case STAND:
                        handleStand(p);
                        break;
                    case BLACKJACK:
                        handleBlackjack(p);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handle payout when a single player busts
    private void handleBust(RegularPlayer p) {
        p.deductScore(p.getWager());
        dealer.addScore(p.getWager());
    }

    // MODIFIES: this
    // EFFECTS: handle payout when a single player stands
    private void handleStand(RegularPlayer p) {
        if (dealer.getStatus() ==  BUST) {
            p.addScore(p.getWager());
            dealer.deductScore(p.getWager());
        } else if (p.getHandSize() > dealer.getHandSize()) {
            p.addScore(p.getWager());
            dealer.deductScore(p.getWager());
        } else if (p.getHandSize() < dealer.getHandSize()) {
            dealer.addScore(p.getWager());
            p.deductScore(p.getWager());
        }
    }

    // MODIFIES: this
    // EFFECTS: handle payout when a single player gets blackjack
    private void handleBlackjack(RegularPlayer p) {
        p.addScore(p.getWager() * 2);
        dealer.deductScore(p.getWager() * 2);
    }

    // getters
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public Player getDealer() {
        return dealer;
    }

    @Override
    public List<RegularPlayer> getRegularPlayers() {
        return regularPlayerList;
    }

    public CardDeck getCd() {
        return cd;
    }
}

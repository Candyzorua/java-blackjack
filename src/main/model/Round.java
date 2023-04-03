package model;

import exceptions.InvalidRoundStatus;

import java.util.List;

import static model.RoundStatus.BLACKJACK;
import static model.RoundStatus.BUST;

/**
 * A single round in a game of Blackjack
 */

public class Round implements Playable {

    private final int roundNumber;
    private final List<Player> regularPlayerList;
    private final Player dealer;
    private final CardDeck cd;

    // REQUIRES: regularPlayerList has at least as many players as the min. number of players
    //           specified by the game
    // EFFECTS: constructs a new round with given players, given dealer, and fresh deck of cards, which is dealt
    public Round(int roundNumber, List<Player> regularPlayerList, Player dealer) {
        this.roundNumber = roundNumber;
        this.regularPlayerList = regularPlayerList;
        this.dealer = dealer;
        cd = new CardDeck();
        dealCardsToAllPlayers();
    }

    // REQUIRES: wagers.size() must be equal to the number of REGULAR players
    // EFFECTS: sets the wages of all players
    //          order of wages in list is preserved when assigning
    public void setWagersForAllPlayers(List<Integer> wagers) {
        for (int i = 0; i < wagers.size(); i++) {
            Player player = regularPlayerList.get(i);
            Integer wager = wagers.get(i);
            player.setWager(wager);
        }
    }

    // REQUIRES: all players' hands must be empty
    // MODIFIES: this
    // EFFECTS: record all players' initial hand and updates their roundStatus if they get blackjack
    public void dealCardsToAllPlayers() {
        for (Player p : regularPlayerList) {
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
        EventLog.getInstance().logEvent(new Event("Player " + p.getName() + " hit."));
        return p.getStatus();
    }

    // MODIFIES: this
    // EFFECTS: handle payouts when dealer has blackjack
    private void handleDealerBlackjack() {
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
                    default:
                        throw new InvalidRoundStatus();
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handle payout when a single player busts
    private void handleBust(Player p) {
        p.deductScore(p.getWager());
        dealer.addScore(p.getWager());
    }

    // MODIFIES: this
    // EFFECTS: handle payout when a single player stands
    private void handleStand(Player p) {
        if (dealer.getStatus() == BUST) {
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
    private void handleBlackjack(Player p) {
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
    public List<Player> getRegularPlayers() {
        return regularPlayerList;
    }

    public CardDeck getCd() {
        return cd;
    }
}

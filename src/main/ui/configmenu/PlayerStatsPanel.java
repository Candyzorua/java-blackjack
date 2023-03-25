package ui.configmenu;

import model.BGame;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Panel with player stats
 */

public class PlayerStatsPanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    protected final BGame bg;
    protected final PlayerTable playerTable;
    protected final PlayerTable dealerTable;
    protected final PlayerSelector playerSelector;

    // EFFECTS: constructs a panel with player stats
    public PlayerStatsPanel(BGame bg) {
        this.bg = bg;
        playerTable = new PlayerTable();
        dealerTable = new PlayerTable();
        playerSelector = new PlayerSelector();
        this.add(new JScrollPane(playerTable));
        this.add(new JScrollPane(dealerTable));
        this.setSize(WIDTH, HEIGHT);
        this.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
    }

    // MODIFIES: this
    // EFFECTS: reloads the given player and dealer into the components
    public void refreshPlayers(List<Player> players, Player dealer) {
        playerTable.repopulate(players);
        List<Player> dealerList = new ArrayList<>();
        dealerList.add(dealer);
        dealerTable.repopulate(dealerList);
    }
}

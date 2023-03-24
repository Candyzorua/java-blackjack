package gui.configmenu;

import model.Playable;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatsPanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    protected final Playable playable;
    protected final PlayerTable playerTable;
    protected final PlayerTable dealerTable;
    protected final PlayerSelector playerSelector;

    public PlayerStatsPanel(Playable playable) {
        this.playable = playable;
        playerTable = new PlayerTable();
        dealerTable = new PlayerTable();
        playerSelector = new PlayerSelector();
        this.add(new JScrollPane(playerTable));
        this.add(new JScrollPane(dealerTable));
        this.setSize(WIDTH, HEIGHT);
        this.refreshPlayers(playable.getRegularPlayers(), playable.getDealer());
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

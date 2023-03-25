package ui.gameui;

import ui.BGameUI;
import ui.gameui.panels.DealerPanel;
import ui.gameui.panels.PlayerPanel;
import ui.gameui.panels.RegularPlayerPanel;
import ui.gameui.tools.HitTool;
import ui.gameui.tools.RoundTool;
import ui.gameui.tools.StandTool;
import model.Player;
import model.Round;
import model.RoundStatus;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RoundUI extends JPanel {
    private final HashMap<Player, PlayerPanel> playerMap = new HashMap<>();
    private List<Player> playerList;
    private final RoundTool ht;
    private final RoundTool st;
    private final Round round;
    private int currIndex;
    private final BGameUI gui;

    public RoundUI(Round round, BGameUI gui) {
        this.round = round;
        this.gui = gui;

        ht = new HitTool(round, null, null, this);
        ht.createButton(this);
        ht.addListener();

        st = new StandTool(round, null, null, this);
        st.createButton(this);
        st.addListener();

        this.initRound(round);
    }

    // MODIFIES: this
    // EFFECTS: initializes RoundUI to the new round
    public void initRound(Round round) {
        this.constructPlayerMapAndList();
        this.add(makePlayerPanels());
        currIndex = 0;
    }

    // MODIFIES: this
    // EFFECTS: constructs the list of players with dealer last
    //          constructs a map of all players to their panel
    private void constructPlayerMapAndList() {
        playerList = new ArrayList<>(round.getRegularPlayers());
        for (Player p: playerList) {
            playerMap.put(p, new RegularPlayerPanel(p));
        }
        Player dealer = round.getDealer();
        playerList.add(dealer);
        playerMap.put(dealer, new DealerPanel(dealer));
    }

    // EFFECTS: constructs a large panel that holds all player panels
    //          adds player panels to the large panel
    private JPanel makePlayerPanels() {
        JPanel largePanel = new JPanel();
        largePanel.setSize(400, 400);
        largePanel.setLayout(new FlowLayout());

        Collection<PlayerPanel> panels = playerMap.values();

        for (PlayerPanel panel: panels) {
            largePanel.add(panel);
        }
        return largePanel;
    }

    // MODIFIES: this
    // EFFECTS: progresses to the next action for the round
    //          if current player status is pending, take the current player's status
    //          else if this player is the last player, deactivate their panel and calculate payouts
    //          else deactivate this player's panel and take the next player's status
    public void nextMove() {
        Player currPlayer = playerList.get(currIndex);
        if (currPlayer.getStatus() == RoundStatus.PENDING) {
            takeStatus(currPlayer);
       // last player
        } else if (currIndex == playerList.size() - 1) {
            PlayerPanel playerPanel = playerMap.get(currPlayer);
            playerPanel.setActive(false);
            round.handlePayouts();
            gui.initializeClosingPanel();
        } else {
            PlayerPanel playerPanel = playerMap.get(currPlayer);
            playerPanel.setActive(false);
            currIndex++;
            Player nextPlayer = playerList.get(currIndex);
            takeStatus(nextPlayer);
        }
    }

    // MODIFIES: this
    // EFFECTS: activates this player's panel
    //          makes the hit and stand buttons relevant to this player
    private void takeStatus(Player p) {
        PlayerPanel playerPanel = playerMap.get(p);
        playerPanel.setActive(true);
        ht.setPlayer(p);
        ht.setPlayerPanel(playerPanel);
        st.setPlayer(p);
        st.setPlayerPanel(playerPanel);
    }
}

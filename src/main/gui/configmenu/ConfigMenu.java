package gui.configmenu;

import model.BGame;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigMenu extends JPanel {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private final PlayerTable playerTable;
    private final PlayerTable dealerTable;
    private final PlayerSelector playerSelector;

    public ConfigMenu() {
        playerSelector = new PlayerSelector();
        playerTable = new PlayerTable();
        dealerTable = new PlayerTable();
        this.add(new JScrollPane(playerTable));
        this.add(new JScrollPane(dealerTable));
        this.add(new JScrollPane(playerSelector));
        this.setSize(WIDTH, HEIGHT);
    }

    public void refreshPlayers(List<Player> players, Player dealer) {
        playerTable.repopulate(players);
        playerSelector.repopulate(players);

        List<Player> dealerList = new ArrayList<>();
        dealerList.add(dealer);
        dealerTable.repopulate(dealerList);
    }

    public static void main(String[] args) {
        BGame bg = new BGame();
        bg.addPlayer(new Player("Jin", 10));
        bg.addPlayer(new Player("Mik", 10));
        bg.addPlayer(new Player("Amy", 10));
        bg.addPlayer(new Player("Tim", 10));
        bg.addPlayer(new Player("Trace", 10));
        Player vic = new Player("Vic", 100);
        bg.addPlayer(vic);
        bg.setPlayerAsDealer(vic);

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setVisible(true);
        ConfigMenu cm = new ConfigMenu();
        cm.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        frame.add(cm);
        MenuTool rmt = new RemovePlayerTool(bg, cm);
        rmt.createButton(cm);
        rmt.addListener();
        MenuTool cdt = new ChangeDealerTool(bg, cm);
        cdt.createButton(cm);
        cdt.addListener();
    }

    // getters
    public PlayerTable getPlayerTable() {
        return playerTable;
    }

    public PlayerTable getDealerTable() {
        return dealerTable;
    }

    public PlayerSelector getPlayerSelector() {
        return playerSelector;
    }
}

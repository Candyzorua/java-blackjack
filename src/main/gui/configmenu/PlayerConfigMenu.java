package gui.configmenu;

import gui.BGameUI;
import gui.configmenu.tools.AddPlayerTool;
import gui.configmenu.tools.ChangeDealerTool;
import gui.configmenu.tools.MenuTool;
import gui.configmenu.tools.RemovePlayerTool;
import model.BGame;
import model.Playable;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerConfigMenu extends PlayerStatsPanel {
    private final BGameUI gui;

    // EFFECTS: sets up a player configuration menu
    //          that displays stats of players, dealer
    //          has functionality to add, remove players, and set new player as dealer
    public PlayerConfigMenu(Playable bg, BGameUI gui) {
        super(bg);
        this.gui = gui;
        this.setUpConfigOptions();
    }

    // MODIFIES: this
    // EFFECTS: sets up functionality for player configuration
    private void setUpConfigOptions() {
        this.add(new JScrollPane(playerSelector));

        MenuTool rmt = new RemovePlayerTool(playable, this);
        rmt.createButton(this);
        rmt.addListener();

        MenuTool cdt = new ChangeDealerTool(playable, this);
        cdt.createButton(this);
        cdt.addListener();

        AddPlayerTool apt = new AddPlayerTool(playable, this);
        apt.createButton(this);
        apt.createTextField(this);
        apt.addListener();

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new PlayerConfigMenu.ClickHandler());
        this.add(continueButton);
    }

    // MODIFIES: this
    // EFFECTS: reloads the given player and dealer into the components
    @Override
    public void refreshPlayers(List<Player> players, Player dealer) {
        super.refreshPlayers(players, dealer);
        playerSelector.repopulate(players);
    }

    public PlayerSelector getPlayerSelector() {
        return playerSelector;
    }

    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.initializeWagerTaker();
        }
    }
}

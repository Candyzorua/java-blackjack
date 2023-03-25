package ui.configmenu;

import ui.BGameUI;
import ui.configmenu.tools.AddPlayerTool;
import ui.configmenu.tools.ChangeDealerTool;
import ui.configmenu.tools.MenuTool;
import ui.configmenu.tools.RemovePlayerTool;
import model.BGame;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel for configuration of game players
 */

public class PlayerConfigMenu extends PlayerStatsPanel {
    private final BGameUI gui;

    // EFFECTS: sets up a player configuration menu
    //          that displays stats of players, dealer
    //          has functionality to add, remove players, and set new player as dealer
    public PlayerConfigMenu(BGame bg, BGameUI gui) {
        super(bg);
        this.gui = gui;
        this.setUpConfigOptions();
    }

    // MODIFIES: this
    // EFFECTS: sets up functionality for player configuration
    private void setUpConfigOptions() {
        this.add(new JScrollPane(playerSelector));

        MenuTool rmt = new RemovePlayerTool(bg, this);
        rmt.createButton(this);
        rmt.addListener();

        MenuTool cdt = new ChangeDealerTool(bg, this);
        cdt.createButton(this);
        cdt.addListener();

        AddPlayerTool apt = new AddPlayerTool(bg, this);
        apt.createButton(this);
        apt.createTextField(this);
        apt.addListener();

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ClickHandler());
        this.add(continueButton);
    }

    // MODIFIES: this
    // EFFECTS: reloads the given player and dealer into the components
    @Override
    public void refreshPlayers(List<Player> players, Player dealer) {
        super.refreshPlayers(players, dealer);
        playerSelector.repopulate(players);
    }

    private class ClickHandler implements ActionListener {
        @Override
        // EFFECTS: prompts gui to take wagers
        public void actionPerformed(ActionEvent e) {
            gui.initializeWagerTaker();
        }
    }

    // getters
    public PlayerSelector getPlayerSelector() {
        return playerSelector;
    }
}

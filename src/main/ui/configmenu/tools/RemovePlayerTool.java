package ui.configmenu.tools;

import ui.configmenu.PlayerConfigMenu;
import model.BGame;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tool for removing players
 */

public class RemovePlayerTool extends MenuTool {

    public RemovePlayerTool(BGame bg, PlayerConfigMenu cm) {
        super(bg, cm);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Remove this player");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: tries to remove the selected player from the game
        //          outputs an error message to the console if fails
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = getSelectedPlayerName();
            Player toRemove = bg.selectPlayer(name);
            bg.removePlayer(toRemove);
            cm.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        }
    }
}

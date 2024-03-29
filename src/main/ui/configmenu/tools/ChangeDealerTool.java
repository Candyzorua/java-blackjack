package ui.configmenu.tools;

import ui.configmenu.PlayerConfigMenu;
import model.BGame;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tool for changing the dealer
 */

public class ChangeDealerTool extends MenuTool {

    public ChangeDealerTool(BGame bg, PlayerConfigMenu cm) {
        super(bg, cm);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Change to dealer");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new ChangeDealerTool.ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: tries to set the selected player as the dealer;
        //          outputs an error message to the console if fails
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = getSelectedPlayerName();
            Player toSetAsDealer = bg.selectPlayer(name);
            bg.setPlayerAsDealer(toSetAsDealer);
            cm.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        }
    }
}

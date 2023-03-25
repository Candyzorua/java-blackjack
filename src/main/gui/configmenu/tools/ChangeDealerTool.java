package gui.configmenu.tools;

import gui.configmenu.PlayerConfigMenu;
import gui.configmenu.PlayerStatsPanel;
import model.BGame;
import model.Playable;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // EFFECTS: tries to remove the selected player from the game
        //          outputs an error message to the console if fails
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = getSelectedPlayerName();
            Player toSetAsDealer = bg.selectPlayer(name);
            if (!bg.setPlayerAsDealer(toSetAsDealer)) {
                System.out.println("Sorry, unable to set that player as the dealer.");
            } else {
                System.out.println("Player successfully set as dealer.");
            }
            cm.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        }
    }
}

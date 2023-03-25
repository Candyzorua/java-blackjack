package ui.configmenu.tools;

import ui.configmenu.PlayerConfigMenu;
import model.BGame;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            if (!bg.removePlayer(toRemove)) {
                System.out.println("Sorry, unable to remove that player.");
            } else {
                System.out.println("Player successfully removed.");
            }
            cm.refreshPlayers(bg.getRegularPlayers(), bg.getDealer());
        }
    }
}

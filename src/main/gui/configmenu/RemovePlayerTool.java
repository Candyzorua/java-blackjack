package gui.configmenu;

import model.BGame;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePlayerTool extends MenuTool {

    public RemovePlayerTool(BGame bg, ConfigMenu cm) {
        super(bg, cm);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Remove this player");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: tries to remove the selected player from the game
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

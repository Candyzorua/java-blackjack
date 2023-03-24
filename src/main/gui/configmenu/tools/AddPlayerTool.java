package gui.configmenu.tools;

import gui.configmenu.PlayerConfigMenu;
import gui.configmenu.PlayerStatsPanel;
import model.Playable;
import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerTool extends MenuTool {
    private JTextField jtf;

    public AddPlayerTool(Playable playable, PlayerConfigMenu cm) {
        super(playable, cm);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Add player");
        addToParent(parent);
    }


    @Override
    public void addListener() {
        button.addActionListener(new AddPlayerTool.ClickHandler());
    }

    public void createTextField(JComponent parent) {
        jtf = new JTextField(10);
        parent.add(jtf);
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: tries to add the player to the game;
        //          outputs an error message to the console if fails
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = jtf.getText();
            if (!playable.addPlayer(new Player(name, 0))) {
                System.out.println("Sorry, a player with that name already exists.");
            } else {
                System.out.println("Player successfully added.");
            }
            cm.refreshPlayers(playable.getRegularPlayers(), playable.getDealer());
        }
    }
}

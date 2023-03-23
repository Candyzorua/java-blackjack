package gui.configmenu;

import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelector extends JComboBox<String> implements Populatable {
    DefaultComboBoxModel<String> model;

    public PlayerSelector() {
        super();
        model = new DefaultComboBoxModel<String>();
        this.setModel(model);
        this.setBounds(50, 100, 90, 20);
    }

    //MODIFIES: this
    //EFFECTS: clears and repopulates table with given list of players
    public void repopulate(List<Player> players) {
        clearPlayers();
        addPlayers(players);
    }

    //MODIFIES: this
    //EFFECTS: adds players from the given list to the JComboBox
    private void addPlayers(List<Player> players) {
        for (Player p: players) {
            model.addElement(p.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: clears table
    private void clearPlayers() {
        model.removeAllElements();
    }

    public static void main(String[] args) {
        PlayerSelector p = new PlayerSelector();
        JScrollPane jp = new JScrollPane(p);
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.add(jp);
        frame.setVisible(true);
        List<Player> players1 = new ArrayList<>();
        players1.add(new Player("Jin", 1));
        p.repopulate(players1);
    }
}

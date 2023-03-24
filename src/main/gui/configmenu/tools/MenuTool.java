package gui.configmenu.tools;

import gui.configmenu.PlayerConfigMenu;
import gui.configmenu.PlayerStatsPanel;
import gui.configmenu.PlayerSelector;
import model.Playable;

import javax.swing.*;

public abstract class MenuTool {
    protected final Playable playable;
    protected final PlayerConfigMenu cm;
    protected final PlayerSelector ps;
    protected JButton button;

    public MenuTool(Playable playable, PlayerConfigMenu cm) {
        this.playable = playable;
        this.cm = cm;
        this.ps = cm.getPlayerSelector();
        button = null;
    }

    // MODIFIES: this
    // EFFECTS: creates button for tool
    public abstract void createButton(JComponent parent);

    // MODIFIES: this
    // EFFECTS: adds a listener for this tool
    public abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: gets the selected player name
    public String getSelectedPlayerName() {
        return ps.getItemAt(ps.getSelectedIndex());
    }
}

package ui.configmenu.tools;

import ui.configmenu.PlayerConfigMenu;
import ui.configmenu.PlayerSelector;
import model.BGame;

import javax.swing.*;

/**
 * Tool to be used in the player configuration menu
 */

public abstract class MenuTool {
    protected final BGame bg;
    protected final PlayerConfigMenu cm;
    protected final PlayerSelector ps;
    protected JButton button;

    public MenuTool(BGame bg, PlayerConfigMenu cm) {
        this.bg = bg;
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

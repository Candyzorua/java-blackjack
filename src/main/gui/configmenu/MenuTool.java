package gui.configmenu;

import model.BGame;

import javax.swing.*;

public abstract class MenuTool {
    protected final BGame bg;
    protected final ConfigMenu cm;
    protected final PlayerSelector ps;
    protected JButton button;

    public MenuTool(BGame bg, ConfigMenu cm) {
        this.bg = bg;
        this.cm = cm;
        this.ps = cm.getPlayerSelector();
        button = null;
    }

    // MODIFIES: this
    // EFFECTS: creates button for tool
    protected abstract void createButton(JComponent parent);

    // MODIFIES: this
    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

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

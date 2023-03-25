package ui.wagertaker;

import model.BGame;

import javax.swing.*;

public abstract class GameTool {
    protected final BGame bg;
    protected JButton button;

    public GameTool(BGame bg) {
        this.bg = bg;
        this.button = null;
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
}

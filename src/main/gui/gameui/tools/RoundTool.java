package gui.gameui.tools;

import gui.gameui.RoundUI;
import gui.gameui.panels.PlayerPanel;
import model.Player;
import model.Round;

import javax.swing.*;

public abstract class RoundTool {
    protected final Round round;
    protected PlayerPanel playerPanel;
    protected JButton button;
    protected Player player;
    protected RoundUI roundUI;

    public RoundTool(Round round, Player player, PlayerPanel playerPanel, RoundUI roundUI) {
        this.round = round;
        this.player = player;
        this.button = null;
        this.playerPanel = playerPanel;
        this.roundUI = roundUI;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }
}

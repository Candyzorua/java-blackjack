package ui.gameui.tools;

import ui.gameui.RoundUI;
import ui.gameui.panels.PlayerPanel;
import model.Player;
import model.Round;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.RoundStatus.STAND;

public class StandTool extends RoundTool {

    public StandTool(Round round, Player player, PlayerPanel playerPanel, RoundUI roundUI) {
        super(round, player, playerPanel, roundUI);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Stand");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new StandTool.ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: lets the player stand
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setStatus(STAND);
            JLabel statusLabel = playerPanel.getStatusLabel();
            statusLabel.setText(String.valueOf(STAND));
            roundUI.nextMove();
        }
    }
}


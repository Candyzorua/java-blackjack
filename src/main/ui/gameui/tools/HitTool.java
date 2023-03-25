package ui.gameui.tools;

import ui.gameui.HandTable;
import ui.gameui.RoundUI;
import ui.gameui.panels.PlayerPanel;
import model.Player;
import model.Round;
import model.RoundStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitTool extends RoundTool {

    public HitTool(Round round, Player player, PlayerPanel playerPanel, RoundUI roundUI) {
        super(round, player, playerPanel, roundUI);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Hit");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new HitTool.ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: lets the player hit and updates status accordingly
        @Override
        public void actionPerformed(ActionEvent e) {
            RoundStatus newStatus = round.letPlayerHit(player);
            JLabel statusLabel = playerPanel.getStatusLabel();
            HandTable handTable = playerPanel.getHandTable();
            statusLabel.setText(String.valueOf(newStatus));
            handTable.repopulate(player.getHand().getContents());
            roundUI.nextMove();
        }
    }
}


package ui.wagertaker;

import model.BGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoneTool extends GameTool {
    private final WagerTaker wt;

    public DoneTool(BGame bg, WagerTaker wt) {
        super(bg);
        this.wt = wt;
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Done");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new DoneTool.ClickHandler());
    }

    private class ClickHandler implements ActionListener {

        // EFFECTS: updates all player wages in model, starts a new round, closes the panel
        @Override
        public void actionPerformed(ActionEvent e) {
            wt.setPlayerWagers();
        }
    }
}

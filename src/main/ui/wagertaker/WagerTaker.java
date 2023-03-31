package ui.wagertaker;

import ui.BGameUI;
import model.BGame;
import model.Player;
import model.Round;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel for players to input their wages
 */

public class WagerTaker extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private final List<WagerSlider> wagerSliderList;
    private final BGame bg;
    private final BGameUI gui;

    // EFFECTS: constructs a panel for wager taking with given BGame and BGameUI
    public WagerTaker(BGame bg, BGameUI gui) {
        this.bg = bg;
        this.gui = gui;
        this.wagerSliderList = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setUpSliders();
        this.setUpDoneButton();
    }

    // MODIFIES: this
    // EFFECTS: renders the sliders and adds them to the slider list
    public void setUpSliders() {
        List<Player> players = bg.getRegularPlayers();
        for (Player p: players) {
            WagerSlider slider = new WagerSlider(p.getName());
            wagerSliderList.add(slider);
            this.add(slider);
            this.add(slider.getSliderLabel());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up done button
    public void setUpDoneButton() {
        GameTool dt = new DoneTool(bg, this);
        dt.createButton(this);
        dt.addListener();
    }

    // EFFECTS: returns a list of the values of all sliders
    public List<Integer> getSliderValues() {
        List<Integer> sliderValues = new ArrayList<>();
        for (WagerSlider ws: wagerSliderList) {
            int wager = ws.getValue();
            sliderValues.add(wager);
        }
        return sliderValues;
    }

    // EFFECTS: sets player wagers
    //          and tells the gui to start the round UI
    public void setPlayerWagers() {
        List<Integer> wagers = this.getSliderValues();
        Round newRound = bg.startRound();
        newRound.setWagersForAllPlayers(wagers);
        gui.initializeRoundUI(newRound);
    }
}

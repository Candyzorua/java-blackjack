package ui.wagertaker;

import javax.swing.*;

/**
 * A slider to take a single player's wager
 */

public class WagerSlider extends JSlider {
    private final JLabel sliderLabel;

    // EFFECTS: constructs a slider titled with the given name
    public WagerSlider(String name) {
        super(JSlider.HORIZONTAL, 0, 50, 25);
        this.setMinorTickSpacing(2);
        this.setMajorTickSpacing(10);
        this.setPaintLabels(true);
        this.setPaintTicks(true);
        this.setSnapToTicks(true);
        this.sliderLabel = new JLabel(name);
    }

    // getters
    public JLabel getSliderLabel() {
        return sliderLabel;
    }
}

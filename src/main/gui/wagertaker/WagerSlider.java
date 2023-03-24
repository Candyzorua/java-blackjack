package gui.wagertaker;

import javax.swing.*;
import java.awt.*;

public class WagerSlider extends JSlider {
    private final JLabel sliderLabel;

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

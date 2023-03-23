package gui;

import javax.swing.*;
import java.awt.event.*;

public class ComboBoxExample {
    JFrame f1;

    ComboBoxExample() {
        f1 = new JFrame("ComboBox Example");
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);
        JButton b = new JButton("Show");
        b.setBounds(200, 100, 75, 20);
        String[] languages = {"C", "C++", "C#", "Java", "PHP"};
        final JComboBox<String> cb = new JComboBox<String>(languages);
        cb.setBounds(50, 100, 90, 20);
        f1.add(cb);
        f1.add(label);
        f1.add(b);
        f1.setLayout(null);
        f1.setSize(350, 350);
        f1.setVisible(true);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "Programming language Selected: "
                        + cb.getItemAt(cb.getSelectedIndex());
                label.setText(data);
            }
        });
    }

    public static void main(String[] args) {
        new ComboBoxExample();
    }
}

package view.entry;

import javax.swing.*;
import java.awt.*;

public class EntryForm extends JPanel {
    final JPanel panel = new JPanel();

    public EntryForm() {
        setupView();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void setupView() {
        panel.setPreferredSize(new Dimension(415, 400));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(232, 218, 232));

        this.add(panel);
        this.setVisible(true);
    }

    void setupLabels(GridBagConstraints constraints, JLabel registrationLabel, JPanel panel) {
        registrationLabel.setFont(new Font("Serif", Font.BOLD, 23));
        registrationLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets.bottom = 20;
        panel.add(registrationLabel, constraints);

        constraints.gridwidth = 1;
        constraints.insets.bottom = 0;
    }

    void setupLabel(GridBagConstraints constraints, String labelText, int gridy) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(60, 20));
        label.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.insets.top = 10;
        panel.add(label, constraints);
    }

    void setupTextField(GridBagConstraints constraints, JTextField textField, int gridy) {
        textField.setPreferredSize(new Dimension(250, 40));
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.insets.left = 10;
        constraints.insets.top = 10;
        panel.add(textField, constraints);
        constraints.insets.left = 0;
    }
}

package view;

import javax.swing.*;

public class ErrorPane {
    public void displayError(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}

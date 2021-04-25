package view;

import ui.CustomButtonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm {
    private final JPanel panel = new JPanel();
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JButton loginButton = new JButton("Войти");

    public LoginForm() {
        setupView();
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordTextField.getPassword());
    }

    //MARK: private methods

    private void setupView() {
        panel.setPreferredSize(new Dimension(415, 400));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(232, 218, 232));
        setupTextFieldsAndLabels();
        setupLoginButton();
    }

    private void setupTextFieldsAndLabels() {
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel registrationLabel = new JLabel("Авторизация");
        registrationLabel.setFont(new Font("Serif", Font.BOLD, 23));
        registrationLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets.bottom = 20;
        panel.add(registrationLabel, constraints);

        constraints.gridwidth = 1;
        constraints.insets.bottom = 0;
        setupLabel(constraints, "Email", 1);
        setupTextField(constraints, emailTextField,  1);
        setupLabel(constraints, "Пароль", 2);
        setupTextField(constraints, passwordTextField,  2);
    }

    private void setupLabel(GridBagConstraints constraints, String labelText, int gridy) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(60, 20));
        label.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.insets.top = 10;
        panel.add(label, constraints);
    }

    private void setupTextField(GridBagConstraints constraints, JTextField textField, int gridy) {
        textField.setPreferredSize(new Dimension(250, 40));
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.insets.left = 10;
        constraints.insets.top = 10;
        panel.add(textField, constraints);
        constraints.insets.left = 0;
    }

    private void setupLoginButton() {
        GridBagConstraints constraints = new GridBagConstraints();

        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setBackground(new Color(0xF7A962E0, true));
        loginButton.setForeground(Color.white);
        loginButton.setUI(new CustomButtonStyle());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.insets.top = 20;

        panel.add(loginButton, constraints);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO: handle login button pressing
            }
        });
    }
}

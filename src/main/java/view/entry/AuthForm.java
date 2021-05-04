package view.entry;

import control.UserController;
import entity.User;
import password.Password;
import ui.CustomButtonStyle;

import javax.swing.*;
import java.awt.*;

public class AuthForm extends EntryForm {
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JButton loginButton = new JButton("Войти");

    public AuthForm() {
        super();
        setupAuthView();
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordTextField.getPassword());
    }

    private void setupAuthView() {
        setupTextFieldsAndLabels();
        setupLoginButton();
    }

    private void setupTextFieldsAndLabels() {
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel registrationLabel = new JLabel("Авторизация");
        setupLabels(constraints, registrationLabel, panel);
        setupLabel(constraints, "Email", 1);
        setupTextField(constraints, emailTextField, 1);
        setupLabel(constraints, "Пароль", 2);
        setupTextField(constraints, passwordTextField, 2);
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

        loginButton.addActionListener(e -> {
            UserController userController = new UserController();
            User user = userController.getUserByEmail(getEmail());
            if (Password.checkPassword(getPassword(), user.getPassword())) {
                System.out.println("авторизован");
            } else {
                System.out.println("неверный пароль");
            }

        });
    }
}

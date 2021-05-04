package view.entry;

import control.UserController;
import entity.User;
import password.Password;
import view.MainForm;

import javax.swing.*;
import java.awt.*;

public class AuthForm extends EntryForm {
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JButton loginButton = new JButton("Войти");
    private final LoginForm loginForm;

    public AuthForm(LoginForm loginForm) {
        super();
        this.loginForm = loginForm;
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
        setupButton(loginButton, panel);

        loginButton.addActionListener(e -> {
            UserController userController = new UserController();
            User user = userController.getUserByEmail(getEmail());
            if (Password.checkPassword(getPassword(), user.getPassword())) {
                new MainForm(user);
                System.out.println("авторизован");
                loginForm.dispose();
            } else {
                System.out.println("неверный пароль");
            }

        });
    }
}

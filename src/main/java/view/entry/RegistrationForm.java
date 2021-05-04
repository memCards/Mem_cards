package view.entry;

import control.UserController;
import entity.User;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends EntryForm {
    private final JTextField firstNameTextField = new JTextField();
    private final JTextField lastNameTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JButton registerButton = new JButton("Зарегистрироваться");

    public RegistrationForm() {
        super();
        setupRegisterView();
    }

    public String getFirstName() {
        return firstNameTextField.getText();
    }

    public String getLastName() {
        return lastNameTextField.getText();
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordTextField.getPassword());
    }

    private void setupRegisterView() {
        setupTextFieldsAndLabels();
        setupRegisterButton();
    }

    private void setupTextFieldsAndLabels() {
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel registrationLabel = new JLabel("Регистрация");
        setupLabels(constraints, registrationLabel, panel);
        setupLabel(constraints, "Имя", 1);
        setupTextField(constraints, firstNameTextField, 1);
        setupLabel(constraints, "Фамилия", 2);
        setupTextField(constraints, lastNameTextField, 2);
        setupLabel(constraints, "Email", 3);
        setupTextField(constraints, emailTextField, 3);
        setupLabel(constraints, "Пароль", 4);
        setupTextField(constraints, passwordTextField, 4);
    }

    private void setupRegisterButton() {
        setupButton(registerButton, panel);

        registerButton.addActionListener(e -> {
            UserController userController = new UserController();
            User user = new User(getEmail(), getFirstName(), getLastName(), getPassword());
            userController.addUser(user);
        });
    }
}

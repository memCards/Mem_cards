package view;

import control.UserController;
import entity.User;
import ui.ButtonStyle;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JPanel {
    private final JPanel panel = new JPanel();
    private final JTextField firstNameTextField = new JTextField();
    private final JTextField lastNameTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JButton registerButton = new JButton("Зарегистрироваться");

    public RegistrationForm() {
        setupView();
    }

    public JPanel getPanel() {
        return panel;
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

    //MARK: private methods

    private void setupView() {
        panel.setPreferredSize(new Dimension(415, 415));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(232, 218, 232));
        setupTextFieldsAndLabels();
        setupRegisterButton();

        this.add(panel);
        this.setVisible(true);
    }

    private void setupTextFieldsAndLabels() {
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel registrationLabel = new JLabel("Регистрация");
        registrationLabel.setFont(new Font("Serif", Font.BOLD, 23));
        registrationLabel.setForeground(Color.DARK_GRAY);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets.bottom = 20;
        panel.add(registrationLabel, constraints);

        constraints.gridwidth = 1;
        constraints.insets.bottom = 0;
        setupLabel(constraints, "Имя", 1);
        setupTextField(constraints, firstNameTextField, 1);
        setupLabel(constraints, "Фамилия", 2);
        setupTextField(constraints, lastNameTextField, 2);
        setupLabel(constraints, "Email", 3);
        setupTextField(constraints, emailTextField, 3);
        setupLabel(constraints, "Пароль", 4);
        setupTextField(constraints, passwordTextField, 4);
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

    private void setupRegisterButton() {
        GridBagConstraints constraints = new GridBagConstraints();

        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setBackground(new Color(0xF7A962E0, true));
        registerButton.setForeground(Color.white);
        registerButton.setUI(new ButtonStyle());
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.insets.top = 20;

        panel.add(registerButton, constraints);

        registerButton.addActionListener(e -> {
            UserController userController = new UserController();
            User user = new User(getEmail(), getFirstName(), getLastName(), getPassword());
            userController.addUser(user);
        });
    }
}

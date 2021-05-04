package view.entry;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import view.FrameLocation;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private enum CurrentPanel {AUTH, REGISTER}

    private JPanel mainPanel;
    private JPanel loginPanel;
    private JButton backButton;
    private CurrentPanel currentPanel = CurrentPanel.AUTH;

    public LoginForm() {
        $$$setupUI$$$();
        this.setContentPane(mainPanel);
        initButtons();

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FrameLocation.setFrameLocation(this);
        this.setVisible(true);
    }

    private void initButtons() {
        backButton.addActionListener(e -> {
            if (currentPanel == CurrentPanel.AUTH) {
                currentPanel = CurrentPanel.REGISTER;
                ((CardLayout) loginPanel.getLayout()).show(loginPanel, "register");
                backButton.setText("Авторизоваться");
                this.pack();
                return;
            }
            if (currentPanel == CurrentPanel.REGISTER) {
                currentPanel = CurrentPanel.AUTH;
                ((CardLayout) loginPanel.getLayout()).show(loginPanel, "auth");
                backButton.setText("Зарегистрироваться");
                this.pack();
            }
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(loginPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Зарегистрироваться");
        mainPanel.add(backButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        loginPanel = new JPanel(new CardLayout());

        AuthForm auth = new AuthForm(this);
        RegistrationForm register = new RegistrationForm();
        loginPanel.add(auth, "auth");
        loginPanel.add(register, "register");
    }
}
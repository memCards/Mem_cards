package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import entity.Card;
import entity.User;
import ui.ButtonStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class MainForm extends JFrame {
    private static final String CARDS = "cardsForm";
    private JButton learnButton;
    private JButton cardsButton;
    private JButton settingsButton;
    private JPanel mainPanel;
    private JPanel currentPanel;
    private final transient User user;

    public MainForm(User user) {
        this.user = user;
        $$$setupUI$$$();
        this.setTitle("Memory cards");
        this.setContentPane(mainPanel);
        addCardsButtonListener();
        learnButtonListener();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        setButtonStyle();
        FrameLocation.setFrameLocation(this);
        this.setVisible(true);
    }

    private void createUIComponents() {
        initCurrentPanel();
    }

    private void initCurrentPanel() {
        currentPanel = new JPanel(new CardLayout());

        CardsListForm cardsListForm = new CardsListForm(user);
        currentPanel.add(cardsListForm, CARDS);
    }

    private void setButtonIcons(JButton button) {
        Image img;
        try {
            img = new ImageIcon(ImageIO
                    .read(getClass()
                            .getResourceAsStream(String.format("/icons/%s.png", button.getName())))).getImage();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int boundary = Math.min(button.getWidth(), button.getHeight());
        Image scaledImg = img
                .getScaledInstance(boundary, boundary, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));
    }

    private void setButtonStyle() {
        ButtonStyle buttonStyle = new ButtonStyle();
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setUI(buttonStyle);
                setButtonIcons((JButton) component);
                component.setBackground(new Color(0xF7A962E0, true));
                component.setForeground(Color.white);
            }
        }
    }

    private void addCardsButtonListener() {
        cardsButton.addActionListener(event -> ((CardLayout) currentPanel.getLayout()).show(currentPanel, CARDS));
    }

    private void learnButtonListener() {
        learnButton.addActionListener(event -> {
            System.out.println("Quiz!");
            QuizForm quizForm = new QuizForm(user);
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
        mainPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(currentPanel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 550), null, 0, false));
        learnButton = new JButton();
        learnButton.setDefaultCapable(true);
        learnButton.setHorizontalAlignment(0);
        learnButton.setName("learn");
        learnButton.setText("");
        mainPanel.add(learnButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), null, null, 0, false));
        cardsButton = new JButton();
        cardsButton.setName("cards");
        cardsButton.setText("");
        mainPanel.add(cardsButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), null, null, 0, false));
        settingsButton = new JButton();
        settingsButton.setHorizontalTextPosition(0);
        settingsButton.setName("settings");
        settingsButton.setText("");
        mainPanel.add(settingsButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

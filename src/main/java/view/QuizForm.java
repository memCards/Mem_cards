package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import control.UserController;
import entity.Card;
import entity.User;
import ui.ButtonStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class QuizForm extends JFrame {
    private transient User user;
    private JPanel mainPanel;
    private JLabel questionLabel;
    private JTextField questionTxt;
    private JLabel answerField;
    private JTextField answerTxt;
    private JButton exitButton;
    private JButton nextQuestionButton;

    private JButton correctAnsButton;
    private JTextField correctAnsField;
    private transient List<Card> quizList;
    private int i = 0;
    private boolean checkCorrect = false;

    public QuizForm(User user) {
        this.user = user;
        $$$setupUI$$$();
        this.setTitle("Quiz");
        this.setContentPane(mainPanel);
        setButtonStyle();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 270));
        this.pack();
        FrameLocation.setFrameLocation(this);
        setVisible(true);
        exitButtonListener();
        initQuizList();
        nextQuestionButtonListener();
        correctAnsButtonListener();
    }

    private void exitButtonListener() {
        exitButton.addActionListener(event -> this.setVisible(false));
    }

    private void correctAnsButtonListener() {
        correctAnsButton.addActionListener(event -> {
            correctAnsField.setText(quizList.get(i).getAnswer());
            checkCorrect = true;
        });
    }

    private void nextQuestionButtonListener() {
        nextQuestionButton.addActionListener(event -> {
            String answer = answerTxt.getText();
            answer = answer.replaceAll("\\s+", "").toUpperCase();
            String correctAnswer = quizList.get(i).getAnswer();
            correctAnswer = correctAnswer.replaceAll("\\s+", "").toUpperCase();
            if (i == quizList.size() - 1) {
                this.setVisible(false);
                JOptionPane.showMessageDialog(mainPanel,
                        "Вы прошли викторину!");
            } else if (answer.equals(correctAnswer) || checkCorrect) {
                correctAnsField.setText("");
                checkCorrect = false;
                answerTxt.setText("");
                i++;
                quiz(i);
            } else {
                JOptionPane.showMessageDialog(mainPanel,
                        "Ответ неверный!");
            }
        });
    }

    private void initQuizList() {
        user = new UserController().getUserByEmail(user.getEmail());
        Set<Card> cards = user.getCards();
        if (cards.isEmpty()) {
            this.setVisible(false);
            JOptionPane.showMessageDialog(mainPanel,
                    "У Вас еще нет карточек!");
        } else {
            quizList = new ArrayList<>();
            quizList.addAll(cards);
            Collections.shuffle(quizList);
            quiz(i);
        }
    }

    private void quiz(int i) {
        questionTxt.setText(quizList.get(i).getQuestion());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void setButtonStyle() {
        ButtonStyle buttonStyle = new ButtonStyle();
        for (Component component : mainPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setUI(buttonStyle);
                component.setBackground(new Color(0xF7A962E0, true));
                component.setForeground(Color.white);
            }
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(5, 6, new Insets(0, 0, 0, 0), -1, -1));
        answerTxt = new JTextField();
        mainPanel.add(answerTxt, new GridConstraints(2, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Выход");
        mainPanel.add(exitButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        questionTxt = new JTextField();
        questionTxt.setEditable(false);
        questionTxt.setEnabled(true);
        Font questionTxtFont = this.$$$getFont$$$("Arial", -1, 12, questionTxt.getFont());
        if (questionTxtFont != null) questionTxt.setFont(questionTxtFont);
        mainPanel.add(questionTxt, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        questionLabel = new JLabel();
        Font questionLabelFont = this.$$$getFont$$$(null, -1, 14, questionLabel.getFont());
        if (questionLabelFont != null) questionLabel.setFont(questionLabelFont);
        questionLabel.setText("Вопрос");
        mainPanel.add(questionLabel, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        answerField = new JLabel();
        Font answerFieldFont = this.$$$getFont$$$(null, -1, 14, answerField.getFont());
        if (answerFieldFont != null) answerField.setFont(answerFieldFont);
        answerField.setText("Ваш ответ:");
        mainPanel.add(answerField, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial", Font.BOLD, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12449917));
        label1.setText("Викторина");
        mainPanel.add(label1, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextQuestionButton = new JButton();
        nextQuestionButton.setText("Следующий вопрос");
        mainPanel.add(nextQuestionButton, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        correctAnsField = new JTextField();
        correctAnsField.setEditable(false);
        Font correctAnsFieldFont = this.$$$getFont$$$("Arial", -1, 12, correctAnsField.getFont());
        if (correctAnsFieldFont != null) correctAnsField.setFont(correctAnsFieldFont);
        mainPanel.add(correctAnsField, new GridConstraints(3, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        correctAnsButton = new JButton();
        correctAnsButton.setText("Правильный ответ");
        mainPanel.add(correctAnsButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}

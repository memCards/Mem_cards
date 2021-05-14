package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import control.CardController;
import entity.Card;
import ui.ButtonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditForm extends JFrame {
    private JPanel mainPanel;
    private JTextField questionTextField;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextPane answerTextPane;
    private JButton saveButton;

    private final transient Card card;
    private final transient CardController cardController;
    private final CardsListForm cardsListForm;

    public EditForm(Card card, CardController cardController, CardsListForm cardsListForm) {
        this.card = card;
        this.cardController = cardController;
        this.cardsListForm = cardsListForm;

        this.setPreferredSize(new Dimension(300, 270));
        this.setTitle("Редактирование");
        this.setContentPane(mainPanel);
        initCardInfo();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        FrameLocation.setFrameLocation(this);
        setVisible(true);

        setupButton();
    }

    private void initCardInfo() {
        questionTextField.setText(card.getQuestion());
        answerTextPane.setText(card.getAnswer());
    }

    private void setupButton() {
        saveButton.setUI(new ButtonStyle());
        saveButton.setBackground(new Color(0xF7A962E0, true));
        saveButton.setForeground(Color.WHITE);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                card.setQuestion(questionTextField.getText());
                card.setAnswer(answerTextPane.getText());
                cardController.updateCard(card);
                cardsListForm.updateList();
                dispose();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        mainPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        questionTextField = new JTextField();
        mainPanel.add(questionTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        questionLabel = new JLabel();
        questionLabel.setText("Вопрос");
        mainPanel.add(questionLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        answerLabel = new JLabel();
        answerLabel.setText("Ответ");
        mainPanel.add(answerLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        answerTextPane = new JTextPane();
        mainPanel.add(answerTextPane, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Сохранить");
        mainPanel.add(saveButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}

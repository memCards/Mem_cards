package view;

import control.CardController;
import entity.Card;
import ui.ButtonStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditForm extends JFrame{
    private JPanel mainPanel;
    private JTextField questionTextField;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextPane answerTextPane;
    private JButton saveButton;

    private final Card card;
    private final CardController cardController;

    public EditForm(Card card, CardController cardController) {
        this.card = card;
        this.cardController = cardController;

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
                dispose();
            }
        });
    }
}

package view;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import control.CategoryController;
import entity.Card;
import entity.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryForm extends JPanel {
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JButton newCategoryButton;
    private JTextField newCategoryField;
    private final List<JCheckBox> checkBoxList = new ArrayList<>();

    public CategoryForm() {
        $$$setupUI$$$();
        initList();
        this.add(mainPanel);
        this.setVisible(true);
        initButtons();
        initFields();
    }

    public List<JCheckBox> getCheckBoxList() {
        return checkBoxList;
    }

    public Set<Category> getCategories() {
        Set<Category> categories = new HashSet<>();

        checkBoxList.forEach(category -> {
            if (category.isSelected()) {
                Category cat = new Category();
                cat.setCategoryName(category.getText());
                categories.add(cat);
            }
        });

        return categories;
    }

    public void selectCardCategories(Card card) {
        Set<Category> categories = card.getCategories();
        Set<String> categoriesNames = new HashSet<>();
        categories.forEach(category -> categoriesNames.add(category.getCategoryName()));
        checkBoxList.forEach(categoryButton -> {
            if (categoriesNames.contains(categoryButton.getText())) {
                categoryButton.setSelected(true);
            }
        });
    }

    private void initList() {
        CategoryController categoryController = new CategoryController();

        List<Category> categories = categoryController.getAllCategories();

        categories.forEach(this::addCategoryBox);
    }

    private void initButtons() {
        newCategoryButton.setBackground(new Color(0xF7A962E0, true));
        newCategoryButton.setForeground(Color.white);
        newCategoryButton.addActionListener(e -> {
            newCategoryButton.setVisible(false);
            newCategoryField.setVisible(true);
            newCategoryField.requestFocus();
            mainPanel.updateUI();
        });
    }

    private void initFields() {
        newCategoryField.setVisible(false);
        newCategoryField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    CategoryController categoryController = new CategoryController();
                    Category category = new Category();
                    category.setCategoryName(newCategoryField.getText());
                    categoryController.addCategory(category);
                    newCategoryButton.setVisible(true);
                    newCategoryField.setText("");
                    newCategoryField.setVisible(false);
                    addCategoryBox(category);
                    mainPanel.updateUI();
                }
            }
        });
    }

    private void addCategoryBox(Category category) {
        JCheckBox checkBox = new JCheckBox(category.getCategoryName());
        listPanel.add(checkBox);
        checkBoxList.add(checkBox);
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
        mainPanel.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,center:d:grow"));
        mainPanel.setAutoscrolls(true);
        scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        scrollPane.setMaximumSize(new Dimension(200, 40));
        scrollPane.setVerifyInputWhenFocusTarget(true);
        scrollPane.setVerticalScrollBarPolicy(20);
        CellConstraints cc = new CellConstraints();
        mainPanel.add(scrollPane, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.FILL));
        listPanel.setAutoscrolls(true);
        listPanel.setInheritsPopupMenu(false);
        listPanel.setMaximumSize(new Dimension(-1, -1));
        scrollPane.setViewportView(listPanel);
        newCategoryButton = new JButton();
        newCategoryButton.setText("Новая категория");
        mainPanel.add(newCategoryButton, cc.xy(1, 1));
        newCategoryField = new JTextField();
        newCategoryField.setEditable(true);
        newCategoryField.setText("");
        mainPanel.add(newCategoryField, cc.xy(1, 3));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        listPanel = new JPanel(new GridLayout(0, 1));
    }
}
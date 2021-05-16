package entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Category {
    private String categoryName;
    private Set<Card> cards = new HashSet<>();

    public Category() {
        // default constructor
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryName.equals(category.categoryName) &&
                Objects.equals(cards, category.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, cards);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}

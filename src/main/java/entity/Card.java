package entity;

import java.util.HashSet;
import java.util.Set;

public class Card {
    private Long id;
    private String question;
    private String answer;
    private Set<User> users = new HashSet<>();

    public Card() {
        // default constructor for Hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

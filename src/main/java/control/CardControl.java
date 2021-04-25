package control;

import entity.Card;
import org.hibernate.Session;
import connection.HibernateConnection;

public class CardControl {
    public void addCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        }
    }

    public void updateCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        }
    }

    public void deleteCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        }
    }

    public Card getCardById(Long cardId) {
        Card card = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            card = session.load(Card.class, cardId);
        } catch (Exception ex) {
            // TODO error form
        }
        return card;
    }
}

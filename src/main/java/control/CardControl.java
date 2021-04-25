package control;

import entity.Card;
import org.hibernate.Session;
import connection.HibernateConnection;

public class CardControl {
    public void addCard(Card card) {
        Session session = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateCard(Card card) {
        Session session = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteCard(Card card) {
        Session session = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO error form
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Card getCardById(Long card_id) {
        Session session = null;
        Card card = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            card = session.load(Card.class, card_id);
        } catch (Exception ex) {
            // TODO error form
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return card;
    }
}

package control;

import connection.HibernateConnection;
import entity.Card;
import org.hibernate.Session;
import view.ErrorPane;

public class CardController {
    public void addCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
    }

    public void updateCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
    }

    public void deleteCard(Card card) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(card);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
    }

    public Card getCardById(Long cardId) {
        Card card = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            card = session.load(Card.class, cardId);
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
        return card;
    }
}

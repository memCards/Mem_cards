package control;

import connection.HibernateConnection;
import entity.User;
import org.hibernate.Session;
import view.ErrorPane;

import javax.persistence.PersistenceException;

public class UserController {

    public void addUser(User user) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            new ErrorPane().displayError("Пользователь с таким email уже существует",
                    "Ошибка регистрации");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser(User user) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(User user) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            user = session.load(User.class, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}

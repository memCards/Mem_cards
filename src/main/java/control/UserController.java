package control;

import connection.HibernateConnection;
import entity.User;
import org.hibernate.Session;

public class UserController {

    public void addUser(User user) {
        Session session = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User getUserByEmail(String email) {
        Session session = null;
        User user = null;
        try {
            session = HibernateConnection.getSessionFactory().openSession();
            user = session.load(User.class, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}

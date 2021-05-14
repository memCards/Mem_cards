package connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.ErrorPane;

public class HibernateConnection {
    private HibernateConnection() {
    }

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Session exception");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
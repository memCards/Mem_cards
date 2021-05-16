package control;

import connection.HibernateConnection;
import entity.Category;
import view.ErrorPane;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CategoryController {
    public void addCategory(Category category) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError("Категория с таким названием уже существует",
                "Ошибка создания категории!");
        }
    }

    public void updateCategory(Category category) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
    }

    public void deleteCategory(Category category) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
    }

    public Category getCategoryByName(String categoryName) {
        Category category = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            category = session.load(Category.class, categoryName);
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
        }
        return category;
    }

    public List<Category> getAllCategories() {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            CriteriaQuery<Category> criteria = session.getCriteriaBuilder().createQuery(Category.class);
            criteria.from(Category.class);
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            new ErrorPane().displayError(ex.getLocalizedMessage(), "Exception");
            return new ArrayList<>();
        }
    }
}

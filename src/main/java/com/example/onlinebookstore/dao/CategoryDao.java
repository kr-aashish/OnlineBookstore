package com.example.onlinebookstore.dao;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    public void saveCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCategory(int categoryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                session.delete(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int categoryId) {
        try (@SuppressWarnings("resource")
        Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getAllCategories() {
        try (@SuppressWarnings("resource")
        Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Category", Category.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addBookToCategory(int categoryId, int bookId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Category category = session.get(Category.class, categoryId);
            Book book = session.get(Book.class, bookId);

            if (category != null && book != null) {
                category.getBooks().add(book);
                session.update(category);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

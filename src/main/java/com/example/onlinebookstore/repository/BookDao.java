package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.Category;
import com.example.onlinebookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {
    public Book saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
    }

    public Book updateBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
    }

    public boolean deleteBook(int bookId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            if (book != null) {
                session.delete(book);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Book getBookById(int bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery("SELECT b FROM Book b JOIN FETCH b.category WHERE b.bookId = :bookId", Book.class);
            query.setParameter("bookId", bookId);
            List<Book> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                return resultList.get(0);
            } else {
                return null; // Book with the specified ID not found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Book> getAllBooks() {
        try (@SuppressWarnings("resource")
        Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT b FROM Book b JOIN FETCH b.category", Book.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public void addCategoryToBook(int bookId, int categoryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Book book = session.get(Book.class, bookId);
            Category category = session.get(Category.class, categoryId);

            if (book != null && category != null) {
                book.setCategory(category);
                session.update(book);
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

package com.example.onlinebookstore.service;

import com.example.onlinebookstore.api.model.Book;
import com.example.onlinebookstore.api.model.Category;
import com.example.onlinebookstore.dao.BookDao;
import com.example.onlinebookstore.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookDao bookDao;
    private final CategoryDao categoryDao;

    @Autowired
    public BookService(BookDao bookDao, CategoryDao categoryDao) {
        this.bookDao = bookDao;
        this.categoryDao = categoryDao;
    }

    public Optional<Book> getBook(int bookId) {
        return Optional.ofNullable(bookDao.getBookById(bookId));
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Book createBook(Book book) {
        return bookDao.saveBook(book);
    }

    public Book updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    public boolean deleteBook(Integer bookId) {
        return bookDao.deleteBook(bookId);
    }

    public void addCategoryToBook(int bookId, Category category) {
        Book book = bookDao.getBookById(bookId);
        if (book != null) {
            Category existingCategory = categoryDao.getCategoryById(category.getCategoryId());
            if (existingCategory == null) {
                categoryDao.saveCategory(category);
            } else {
                category = existingCategory;
            }
            book.setCategory(category);
            bookDao.updateBook(book);
        }
    }

}

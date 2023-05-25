package com.example.springapi.service;

import com.example.springapi.api.model.Book;
import com.example.springapi.api.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> bookList = new ArrayList<Book>();

    public BookService() {
        bookList = new ArrayList<>();

        // Create dummy entries
        Category dummyCategory = new Category("dummyCategory");
        Book bookOne = new Book(1, "BookOne","authorOne", 23, dummyCategory);
        Book bookTwo = new Book(2, "BookTwo","authorTwo", 25, dummyCategory);

        bookList.addAll(Arrays.asList(bookOne, bookTwo));
    }

    public Optional<Book> getBook(Integer bookId) {
        Optional optionalBook = Optional.empty();

        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                optionalBook = Optional.of(book);
                return optionalBook;
            }
        }

        return optionalBook;
    }
}

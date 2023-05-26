package com.example.onlinebookstore.api.controller;

import com.example.onlinebookstore.api.model.Book;
import com.example.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam Integer bookId) {
        Optional<Book> book = bookService.getBook(bookId);
        return (Book) book.orElse(null);
    }
}
package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable int bookId) {
        Optional<Book> book = bookService.getBook(bookId);
        if (book.isPresent()) {
            logger.info("Book found: {}", book.get());
            return ResponseEntity.ok(book.get()); // 200 OK
        } else {
            logger.warn("Book not found for ID: {}", bookId);
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        logger.info("Retrieved all books. Count: {}", books.size());
        return ResponseEntity.ok(books); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        logger.info("Book created: {}", createdBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook); // 201 Created
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book existingBook = bookService.getBook(book.getBookId()).orElse(null);
        if (existingBook != null) {
            Book updatedBook = bookService.updateBook(book);
            logger.info("Book updated: {}", updatedBook);
            return ResponseEntity.ok(updatedBook); // 200 OK
        } else {
            logger.warn("Book not found for ID: {}", book.getBookId());
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        boolean deleted = bookService.deleteBook(bookId);
        if (deleted) {
            logger.info("Book deleted. ID: {}", bookId);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            logger.warn("Book not found for ID: {}", bookId);
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
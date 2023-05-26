package com.example.onlinebookstore.api.controller;

import com.example.onlinebookstore.api.model.Book;
import com.example.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable int bookId) {
        Optional<Book> book = bookService.getBook(bookId);
        return book.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());// 200 OK
                                                   // 404 Not Found
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook); // 201 Created
    }
    
    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book existingBook = bookService.getBook(book.getBookId()).orElse(null);
        if (existingBook != null) {
            Book updatedBook = bookService.updateBook(book);
            return ResponseEntity.ok(updatedBook); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer bookId) {
        boolean deleted = bookService.deleteBook(bookId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}

package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.repository.BookDao;
import com.example.onlinebookstore.repository.CategoryDao;
import com.example.onlinebookstore.entity.Book;
import com.example.onlinebookstore.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        bookDao.getAllBooks().forEach(book -> bookDao.deleteBook(book.getBookId()));
        categoryDao.saveCategory(new Category("Fiction"));
        bookDao.saveBook(new Book("bookName", "authorName", 100, new Category("Fiction")));
    }

//    @Test
    void shouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].bookId").value("bookName"))
                .andExpect(jsonPath("$[0].categoryId").value("Fiction"));
    }
}

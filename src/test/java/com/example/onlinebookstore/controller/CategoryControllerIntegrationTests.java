package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.repository.CategoryDao;
import com.example.onlinebookstore.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CategoryControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        // Clear database and seed data
        categoryDao.getAllCategories().forEach(category -> categoryDao.deleteCategory(category.getCategoryId()));
        categoryDao.saveCategory(new Category("Fiction"));
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Fiction"));
    }

    @Test
    void shouldCreateCategory() throws Exception {
        String newCategory = "{ \"name\": \"Science Fiction\" }";

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newCategory))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Science Fiction"));
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        Category savedCategory = categoryDao.getAllCategories().get(0);

        mockMvc.perform(get("/categories/" + savedCategory.getCategoryId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(savedCategory.getName()));
    }

    @Test
    void shouldReturn404ForNonExistingCategory() throws Exception {
        mockMvc.perform(get("/categories/9999"))
                .andExpect(status().isNotFound());
    }

    void shouldDeleteCategory() throws Exception {
        Category savedCategory = categoryDao.getAllCategories().get(0);

        mockMvc.perform(delete("/categories/" + savedCategory.getCategoryId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/categories/" + savedCategory.getCategoryId()))
                .andExpect(status().isNotFound());
    }
}

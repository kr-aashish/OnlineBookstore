package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTests {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void shouldReturnCategoryWhenCategoryExists() {
        // Given
        int categoryId = 1;
        Category category = new Category("Fiction");
        when(categoryService.getCategory(categoryId)).thenReturn(category);

        // When
        ResponseEntity<Category> response = categoryController.getCategory(categoryId);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fiction", response.getBody().getName());
        verify(categoryService, times(1)).getCategory(categoryId);
    }

    @Test
    void shouldReturn404WhenCategoryDoesNotExist() {
        // Given
        int categoryId = 999;
        when(categoryService.getCategory(categoryId)).thenReturn(null);

        // When
        ResponseEntity<Category> response = categoryController.getCategory(categoryId);

        // Then
        assertEquals(404, response.getStatusCodeValue());
        verify(categoryService, times(1)).getCategory(categoryId);
    }

    @Test
    void shouldReturnAllCategories() {
        // Given
        List<Category> categories = Arrays.asList(
                new Category("Fiction"),
                new Category("Non-Fiction")
        );
        when(categoryService.getAllCategories()).thenReturn(categories);

        // When
        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void shouldCreateCategory() {
        // Given
        Category newCategory = new Category("Science Fiction");
        when(categoryService.createCategory(newCategory)).thenReturn(newCategory);

        // When
        ResponseEntity<Category> response = categoryController.createCategory(newCategory);

        // Then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Science Fiction", response.getBody().getName());
        verify(categoryService, times(1)).createCategory(newCategory);
    }
}

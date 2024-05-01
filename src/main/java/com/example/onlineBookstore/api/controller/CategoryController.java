package com.example.onlinebookstore.api.controller;

import com.example.onlinebookstore.api.model.Category;
import com.example.onlinebookstore.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable int categoryId) {
        Optional<Category> category = Optional.ofNullable(categoryService.getCategory(categoryId));
        if (category.isPresent()) {
            logger.info("Category found: {}", category.get());
            return ResponseEntity.ok(category.get()); // 200 OK
        } else {
            logger.warn("Category not found for ID: {}", categoryId);
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        logger.info("Retrieved all categories. Count: {}", categories.size());
        return ResponseEntity.ok(categories); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        logger.info("Category created: {}", createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory); // 201 Created
    }
}
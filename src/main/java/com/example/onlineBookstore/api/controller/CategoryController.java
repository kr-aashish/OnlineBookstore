package com.example.onlinebookstore.api.controller;

import com.example.onlinebookstore.api.model.Category;
import com.example.onlinebookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@Validated // Enables validation for this controller
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable @Min(1) int categoryId) {
        Optional<Category> category = Optional.ofNullable(categoryService.getCategory(categoryId));
        return category.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()); // 200 OK or 404 Not Found
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory); // 201 Created
    }
}
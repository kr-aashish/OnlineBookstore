package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category createCategory(Category category) {
        categoryDao.saveCategory(category);
        return category;
    }

    public void deleteCategory(int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }

    public Category getCategory(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public void addBookToCategory(int categoryId, int bookId) {
        categoryDao.addBookToCategory(categoryId, bookId);
    }
}
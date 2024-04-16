package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(String id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(new Category(category.getName()));
    }

    public Category updateCategory(String id, Category category) {
        Optional<Category> updated = categoryRepository.findById(id);
        Category _category = updated.get();
        _category.setName(category.getName());
        return categoryRepository.save(_category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
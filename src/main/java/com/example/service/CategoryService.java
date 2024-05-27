package com.example.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.model.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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
        ObjectId categoryId = new ObjectId(id);

        List<Product> list = productRepository.findByCategory(categoryId);
        productRepository.deleteAll(list);

        categoryRepository.deleteById(id);
    }

    public Page<Category> findAllCategory(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> findByName(int page, int pageSize, String filterName) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return categoryRepository.findByName(filterName, pageable);
    }
}

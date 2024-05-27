package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-all-category")
    public ResponseEntity<?> getAllCategory() {
        try {
            List<Category> list = categoryService.getAllCategory();
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", list));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFilterCategory(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(required = false) String filterName) {
        try {
            Page<Category> list;
            if (filterName == null) {
                list = categoryService.findAllCategory(page, pageSize);
            } else
                list = categoryService.findByName(page, pageSize, filterName);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", list.getContent()));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id) {
        try {
            Optional<Category> category = categoryService.getCategory(id);
            if (category.isPresent()) {
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", category.get()));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            Category created = categoryService.createCategory(category);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", created));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @RequestBody Category category) {
        try {
            Category updated = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

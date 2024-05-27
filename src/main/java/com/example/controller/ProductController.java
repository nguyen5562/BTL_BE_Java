package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<?> getAllProduct() {
        try {
            List<Product> list = productService.getAllProduct();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", list));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFilterProduct(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(required = false) String filterName,
            @RequestParam(required = false) String filterCategory,
            @RequestParam(required = false, defaultValue = "0") float filterPriceMin,
            @RequestParam(required = false, defaultValue = Float.MAX_VALUE + "") float filterPriceMax) {
        try {
            Page<Product> list;
            if (filterName == null && filterCategory == null) {
                list = productService.findAllProduct(page, pageSize, filterPriceMin, filterPriceMax);
            } else if (filterName != null && filterCategory == null) {
                list = productService.findName(page, pageSize, filterName, filterPriceMin, filterPriceMax);
            } else if (filterName == null && filterCategory != null) {
                list = productService.findCategory(page, pageSize, filterCategory, filterPriceMin, filterPriceMax);
            } else
                list = productService.findNameCategory(page, pageSize, filterName, filterCategory, filterPriceMin,
                        filterPriceMax);

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", list.getContent()));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id) {
        try {
            Optional<Product> product = productService.getProduct(id);
            if (product.isPresent()) {
                return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", product.get()));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product created = productService.createProduct(product);
            return ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "SUCCESS",
                "data", created));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "SUCCESS",
                "data", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.controller;

import java.util.List;
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
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> list = productService.getAllProduct();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int pageSize,
        @RequestParam(required = false) String filterName,
        @RequestParam(required = false) String filterBrand,
        @RequestParam(required = false, defaultValue = "0") float filterPriceMin,
        @RequestParam(required = false, defaultValue = Float.MAX_VALUE + "") float filterPriceMax) {
        try {
            Page<Product> list;
            if (filterName == null && filterBrand == null) {
                list = productService.findAllProduct(page, pageSize, filterPriceMin, filterPriceMax);
            } else if (filterName != null && filterBrand == null) {
                list = productService.findName(page, pageSize, filterName, filterPriceMin, filterPriceMax);
            } else if (filterName == null && filterBrand != null) {
                list = productService.findBrand(page, pageSize, filterBrand, filterPriceMin, filterPriceMax);
            } else list = productService.findNameBrand(page, pageSize, filterName, filterBrand, filterPriceMin, filterPriceMax);
            
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(list.getContent(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        try {
            Optional<Product> product = productService.getProduct(id);
            if (product.isPresent()) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product created = productService.createProduct(product);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        try {
            Product updated = productService.updateProduct(id, product);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

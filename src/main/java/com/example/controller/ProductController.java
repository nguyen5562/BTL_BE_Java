package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Product;
import com.example.repository.ProductRepository;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> list = productRepository.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        try {
            Optional<Product> product = productRepository.findById(id);
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
            Product created = productRepository.save(new Product(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getBrand(),
                product.getImage()
            ));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        try {
            Optional<Product> updated = productRepository.findById(id);
            if (updated.isPresent()) {
                Product _product = updated.get();
                _product.setName(product.getName());
                _product.setDescription(product.getDescription());
                _product.setPrice(product.getPrice());
                _product.setStock(product.getStock());
                _product.setCategory(product.getCategory());
                _product.setBrand(product.getBrand());
                _product.setImage(product.getImage());
                return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

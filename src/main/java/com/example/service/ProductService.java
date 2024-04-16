package com.example.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(new Product(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCategory(),
            product.getBrand(),
            product.getImage()));
    }

    public Product updateProduct(String id, Product product) {
        Optional<Product> updated = productRepository.findById(id);
        Product _product = updated.get();
        _product.setName(product.getName());
        _product.setDescription(product.getDescription());
        _product.setPrice(product.getPrice());
        _product.setStock(product.getStock());
        _product.setCategory(product.getCategory());
        _product.setBrand(product.getBrand());
        _product.setImage(product.getImage());
        return productRepository.save(_product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findNameBrand(int page, int pageSize, String filterName, String filterBrand, float filterMinPrice, float filterMaxPrice) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        ObjectId brand = new ObjectId(filterBrand);
        return productRepository.findByNameandBrand(filterName, brand, filterMinPrice, filterMaxPrice, pageable);
    }

    public Page<Product> findName(int page, int pageSize, String filterName, float filterMinPrice, float filterMaxPrice) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return productRepository.findByName(filterName, filterMinPrice, filterMaxPrice, pageable);
    }

    public Page<Product> findBrand(int page, int pageSize, String filterBrand, float filterMinPrice, float filterMaxPrice) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        ObjectId brand = new ObjectId(filterBrand);
        return productRepository.findByBrand(brand, filterMinPrice, filterMaxPrice, pageable);
    }

    public Page<Product> findAllProduct(int page, int pageSize, float filterMinPrice, float filterMaxPrice) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return productRepository.findAllProduct(filterMinPrice, filterMaxPrice, pageable);
    }
}

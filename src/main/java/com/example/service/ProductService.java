package com.example.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Product> findNameCategory(String filterName, String filterCategory) {
        ObjectId category = new ObjectId(filterCategory);
        return productRepository.findByNameandCategory(filterName, category);
    }

    public List<Product> findName(String filterName) {
        return productRepository.findByName(filterName);
    }

    public List<Product> findCategory(String filterCategory) {
        return productRepository.findByCategory(filterCategory);
    }

    public List<Product> findBrand(String filterBrand) {
        return productRepository.findByBrand(filterBrand);
    }
}

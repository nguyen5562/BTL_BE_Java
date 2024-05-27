package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Brand;
import com.example.model.Product;
import com.example.repository.BrandRepository;
import com.example.repository.ProductRepository;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrand(String id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(new Brand(brand.getName()));
    }

    public Brand updateBrand(String id, Brand brand) {
        Optional<Brand> updated = brandRepository.findById(id);
        Brand _brand = updated.get();
        _brand.setName(brand.getName());
        return brandRepository.save(_brand);
    }

    public void deleteBrand(String id) {
        List<Product> list = productRepository.findByBrand(id);
        productRepository.deleteAll(list);
        
        brandRepository.deleteById(id);
    }

    public Page<Brand> findAllBrand(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return brandRepository.findAll(pageable);
    }

    public Page<Brand> findByName(int page, int pageSize, String filterName) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return brandRepository.findByName(filterName, pageable);
    }
}
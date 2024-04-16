package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Brand;
import com.example.repository.BrandRepository;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

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
        brandRepository.deleteById(id);
    }
}

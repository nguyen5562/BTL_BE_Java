package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Brand;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
    
}
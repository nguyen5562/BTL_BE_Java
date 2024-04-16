package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Brand;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<Brand> findByName(String name, Pageable pageable);
}
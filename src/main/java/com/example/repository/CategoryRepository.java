package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<Category> findByName(String name, Pageable pageable);
}

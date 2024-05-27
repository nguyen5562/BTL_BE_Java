package com.example.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{$and: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'category': { $eq: ?1 } } ]}")
    List<Product> findByNameandCategory(String name, ObjectId category);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Product> findByName(String name);

    @Query("{ 'category': { $eq: ?0 } }")
    List<Product> findByCategory(ObjectId category);

    @Query("{ 'brand': { $eq: ?0 } }")
    List<Product> findByBrand(ObjectId brand);

    // @Query("{ 'category': ?0 }")
    // void deleteByCategory(ObjectId category);

    // @Query("{ 'brand': ?0 }")
    // void deleteByBrand(ObjectId brand);
}
    
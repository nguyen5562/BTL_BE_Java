package com.example.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{$and: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'brand': { $eq: ?1 } }, { 'price': { $gte: ?2, $lte: ?3 } } ]}")
    Page<Product> findByNameandBrand(String name, ObjectId brand, float minPrice, float maxPrice, Pageable pageable);

    @Query("{$and: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'price': { $gte: ?1, $lte: ?2 } } ]}")
    Page<Product> findByName(String name, float minPrice, float maxPrice, Pageable pageable);

    @Query("{$and: [ { 'brand': { $eq: ?0 } }, { 'price': { $gte: ?1, $lte: ?2 } } ]}")
    Page<Product> findByBrand(ObjectId brand, float minPrice, float maxPrice, Pageable pageable);

    @Query("{$and: [ { 'price': { $gte: ?0, $lte: ?1 } } ]}")
    Page<Product> findAllProduct(float minPrice, float maxPrice, Pageable pageable);
}
    
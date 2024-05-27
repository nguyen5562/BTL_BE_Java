package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Order;
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'user': { $eq: ?0 } }")
    List<Order> findByUser(String user);
}

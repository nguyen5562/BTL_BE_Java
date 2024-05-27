package com.example.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.OrderItem;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    @Query("{ 'order': { $eq: ?0 } }")
    List<OrderItem> findByOrder(ObjectId order);

    // @Query("{'order': ?0}")
    // void deleteByOrder(ObjectId order);
}

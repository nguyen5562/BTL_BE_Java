package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{$and: [ { 'email': { $eq: ?0 } } ]}")
    User findByEmail(String email);
}

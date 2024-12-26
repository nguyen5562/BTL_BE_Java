package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.model.PhieuNhap;

public interface PhieuNhapRepository extends MongoRepository<PhieuNhap, String> {
    @Query("{ 'user': { $eq: ?0 } }")
    List<PhieuNhap> findByUser(String user);
}

package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.model.PhieuNhapItem;

public interface PhieuNhapItemRepository extends MongoRepository<PhieuNhapItem, String> {
    @Query("{ 'phieunhap': { $eq: ?0 } }")
    List<PhieuNhapItem> findByPhieuNhap(String phieuNhap);
}

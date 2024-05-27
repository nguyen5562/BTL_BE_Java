package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Brand;
import com.example.service.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/get-all-brand")
    public ResponseEntity<?> getAllBrand() {
        try {
            List<Brand> list = brandService.getAllBrand();
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", list));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFilterBrand(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int pageSize,
            @RequestParam(required = false) String filterName) {
        try {
            Page<Brand> list;
            if (filterName == null) {
                list = brandService.findAllBrand(page, pageSize);
            } else
                list = brandService.findByName(page, pageSize, filterName);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", list.getContent()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-brand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable("id") String id) {
        try {
            Optional<Brand> brand = brandService.getBrand(id);
            if (brand.isPresent()) {
                return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", brand.get()));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-brand")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand) {
        try {
            Brand created = brandService.createBrand(brand);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", created));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-brand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") String id, @RequestBody Brand brand) {
        try {
            Brand updated = brandService.updateBrand(id, brand);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS",
                    "data", updated));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") String id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.ok(Map.of(
                    "status", "OK",
                    "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

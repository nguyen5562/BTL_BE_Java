package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Brand;
import com.example.repository.BrandRepository;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<Brand>> getAllBrand() {
        try {
            List<Brand> list = brandRepository.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-brand/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable("id") String id) {
        try {
            Optional<Brand> brand = brandRepository.findById(id);
            if (brand.isPresent()) {
                return new ResponseEntity<>(brand.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-brand")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        try {
            Brand created = brandRepository.save(new Brand(brand.getName()));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-brand/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") String id, @RequestBody Brand brand) {
        try {
            Optional<Brand> updated = brandRepository.findById(id);
            if (updated.isPresent()) {
                Brand _brand = updated.get();
                _brand.setName(brand.getName());
                return new ResponseEntity<>(brandRepository.save(_brand), HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") String id) {
        try {
            brandRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

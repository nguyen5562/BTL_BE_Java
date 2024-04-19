package com.example.controller;

import java.util.List;
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
    public ResponseEntity<List<Brand>> getAllBrand() {
        try {
            List<Brand> list = brandService.getAllBrand();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Brand>> getAllFilterBrand(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(required = false) String filterName) {
        try {
            Page<Brand> list;
            if (filterName == null) {
                list = brandService.findAllBrand(page, pageSize);
            } else
                list = brandService.findByName(page, pageSize, filterName);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(list.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-brand/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable("id") String id) {
        try {
            Optional<Brand> brand = brandService.getBrand(id);
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
            Brand created = brandService.createBrand(brand);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-brand/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") String id, @RequestBody Brand brand) {
        try {
            Brand updated = brandService.updateBrand(id, brand);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-brand/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") String id) {
        try {
            brandService.deleteBrand(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

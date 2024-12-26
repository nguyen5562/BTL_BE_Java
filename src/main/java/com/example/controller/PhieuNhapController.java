package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.PhieuNhapDTO;
import com.example.DTO.PhieuNhapRequest;
import com.example.model.PhieuNhap;
import com.example.service.PhieuNhapService;

@RestController
@RequestMapping("api/phieunhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @PostMapping("/create-phieunhap")
    public ResponseEntity<?> createPhieuNhap(@RequestBody PhieuNhapRequest phieuNhapRequest) {
        try {
            PhieuNhap phieuNhap = phieuNhapService.createPhieuNhap(
                phieuNhapRequest.getNgayNhap(),
                phieuNhapRequest.getTongTien(),
                phieuNhapRequest.getUser(),
                phieuNhapRequest.getPhieuNhapItems()
            );

            return ResponseEntity.ok(Map.of(
                "status", "OK",
                "message", "SUCCESS",
                "data", phieuNhap
            ));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPhieuNhap() {
        try {
            List<PhieuNhapDTO> phieuNhaps = phieuNhapService.getAllPhieuNhaps();
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", phieuNhaps));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-phieunhap/{id}")
    public ResponseEntity<?> getPhieuNhap(@PathVariable("id") String id) {
        try {
            PhieuNhapDTO phieuNhap = phieuNhapService.getPhieuNhap(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", phieuNhap));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-phieunhap-user/{id}")
    public ResponseEntity<?> getPhieuNhapByUser(@PathVariable("id") String id) {
        try {
            List<PhieuNhapDTO> phieuNhaps = phieuNhapService.getAllPhieuNhapsByUser(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", phieuNhaps));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
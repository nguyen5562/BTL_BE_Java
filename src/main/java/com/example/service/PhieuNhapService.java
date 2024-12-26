package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.PhieuNhapDTO;
import com.example.model.PhieuNhap;
import com.example.model.PhieuNhapItem;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.PhieuNhapItemRepository;
import com.example.repository.PhieuNhapRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

@Service
public class PhieuNhapService {
    @Autowired
    private PhieuNhapRepository phieuNhapRepository;

    @Autowired
    private PhieuNhapItemRepository phieuNhapItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public PhieuNhap createPhieuNhap(Date ngayNhap, int tongTien, String user, List<PhieuNhapItem> phieuNhapItems) {
        PhieuNhap phieuNhap = new PhieuNhap();
        phieuNhap.setNgayNhap(ngayNhap);
        phieuNhap.setTongTien(tongTien);
        phieuNhap.setUser(user);

        PhieuNhap savedPhieuNhap = phieuNhapRepository.save(phieuNhap);

        phieuNhapItems.stream().map(item -> {
            PhieuNhapItem phieuNhapItem = new PhieuNhapItem();
            phieuNhapItem.setPhieunhap(savedPhieuNhap.getId());
            phieuNhapItem.setProduct(item.getProduct());
            phieuNhapItem.setName(item.getName());
            phieuNhapItem.setImage(item.getImage());
            phieuNhapItem.setQuantity(item.getQuantity());
            phieuNhapItem.setPrice(item.getPrice());
            phieuNhapItem.setTotalPrice(item.getTotalPrice());

            Product product = productRepository.findById(item.getProduct()).get();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);

            return phieuNhapItemRepository.save(phieuNhapItem);
        }).collect(Collectors.toList());

        return savedPhieuNhap;
    };

    public PhieuNhapDTO getPhieuNhap(String id) {
        Optional<PhieuNhap> phieuNhapOptional = phieuNhapRepository.findById(id);
        if (!phieuNhapOptional.isPresent()) {
            throw new RuntimeException("PhieuNhap not found");
        }

        PhieuNhap phieuNhap = phieuNhapOptional.get();
        List<PhieuNhapItem> phieuNhapItems = phieuNhapItemRepository.findByPhieuNhap(phieuNhap.getId());
        PhieuNhapDTO phieuNhapDTO = new PhieuNhapDTO(phieuNhap, phieuNhapItems);
        return phieuNhapDTO;
    }

    public List<PhieuNhapDTO> getAllPhieuNhaps() {
        List<PhieuNhap> phieuNhaps = phieuNhapRepository.findAll();
        return phieuNhaps.stream().map(phieuNhap -> {
            List<PhieuNhapItem> phieuNhapItems = phieuNhapItemRepository.findByPhieuNhap(phieuNhap.getId());
            User user = userRepository.findById(phieuNhap.getUser()).orElse(null);
            return new PhieuNhapDTO(phieuNhap, phieuNhapItems, user != null ? user.getName() : null);
        }).collect(Collectors.toList());
    }

    public List<PhieuNhapDTO> getAllPhieuNhapsByUser(String user) {
        List<PhieuNhap> phieuNhaps = phieuNhapRepository.findByUser(user);

        return phieuNhaps.stream().map(phieuNhap -> {
            List<PhieuNhapItem> phieuNhapItems = phieuNhapItemRepository.findByPhieuNhap(phieuNhap.getId());
            return new PhieuNhapDTO(phieuNhap, phieuNhapItems);
        }).collect(Collectors.toList());
    }
}

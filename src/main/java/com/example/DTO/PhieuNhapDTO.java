package com.example.DTO;

import java.util.Date;
import java.util.List;

import com.example.model.PhieuNhap;
import com.example.model.PhieuNhapItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhieuNhapDTO {
    @JsonProperty("_id")
    private String id;

    private Date ngayNhap;
    private int tongTien;
    private String user;
    private String userName;
    private List<PhieuNhapItem> phieuNhapItems;

    public PhieuNhapDTO(PhieuNhap phieuNhap, List<PhieuNhapItem> phieuNhapItems) {
        this.id = phieuNhap.getId();
        this.ngayNhap = phieuNhap.getNgayNhap();
        this.tongTien = phieuNhap.getTongTien();
        this.user = phieuNhap.getUser();
        this.phieuNhapItems = phieuNhapItems;
    }

    public PhieuNhapDTO(PhieuNhap phieuNhap, List<PhieuNhapItem> phieuNhapItems, String userName) {
        this(phieuNhap, phieuNhapItems);
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PhieuNhapItem> getPhieuNhapItems() {
        return phieuNhapItems;
    }

    public void setPhieuNhapItems(List<PhieuNhapItem> phieuNhapItems) {
        this.phieuNhapItems = phieuNhapItems;
    }

    
}

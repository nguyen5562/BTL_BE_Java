package com.example.DTO;

import java.util.Date;
import java.util.List;

import com.example.model.PhieuNhapItem;

public class PhieuNhapRequest {
    private Date ngayNhap;
    private int tongTien;
    private String user;
    private List<PhieuNhapItem> phieuNhapItems;

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

    public List<PhieuNhapItem> getPhieuNhapItems() {
        return phieuNhapItems;
    }

    public void setPhieuNhapItems(List<PhieuNhapItem> phieuNhapItems) {
        this.phieuNhapItems = phieuNhapItems;
    }

}

package com.example.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "phieunhaps")
public class PhieuNhap {
    @Id
    @JsonProperty("_id")
    private String id;

    private Date ngayNhap;
    private int tongTien;
    private String user;

    public PhieuNhap() {
    }

    public PhieuNhap(Date ngayNhap, int tongTien, String user) {
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.user = user;
    }

    public String getId() {
        return id;
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

    
}

package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "phieunhapitems")
public class PhieuNhapItem {
    @Id
    @JsonProperty("_id")
    private String id;
    
    private String phieunhap;
    private String product;
    private String name;
    private String image;
    private int quantity;
    private int price;
    private int totalPrice;

    public PhieuNhapItem() {
    }

    public PhieuNhapItem(String phieunhap, String product, String name, String image, int quantity, int price, int totalPrice) {
        this.phieunhap = phieunhap;
        this.product = product;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getPhieunhap() {
        return phieunhap;
    }

    public void setPhieunhap(String phieunhap) {
        this.phieunhap = phieunhap;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    
}

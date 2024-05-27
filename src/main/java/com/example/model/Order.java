package com.example.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "orders")
public class Order {
    @Id
    @JsonProperty("_id")
    private String id;

    private String shippingAddress;
    private String recipient;
    private String phone;
    private Date orderDate;
    private String paymentMethod;
    private int status;
    private int totalPrice;
    private String user;
    
    public Order() {
    }

    public Order(String shippingAddress, String recipient, String phone, Date orderDate, String paymentMethod,
            int status, int totalPrice, String user) {
        this.shippingAddress = shippingAddress;
        this.recipient = recipient;
        this.phone = phone;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public String getId() {
        return id;
    } 

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
}

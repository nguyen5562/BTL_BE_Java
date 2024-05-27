package com.example.DTO;

import java.util.Date;
import java.util.List;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {
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
    private List<OrderItem> orderItems;
    private String userName;

    // Contructor
    public OrderDTO(Order order, List<OrderItem> orderItems) {
        this.id = order.getId();
        this.shippingAddress = order.getShippingAddress();
        this.recipient = order.getRecipient();
        this.phone = order.getPhone();
        this.orderDate = order.getOrderDate();
        this.paymentMethod = order.getPaymentMethod();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.user = order.getUser();
        this.orderItems = orderItems;
    }

    public OrderDTO(Order order, List<OrderItem> orderItems, String userName) {
        this(order, orderItems);
        this.userName = userName;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.example.controller;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.OrderDTO;
import com.example.DTO.OrderRequest;
import com.example.model.Order;
import com.example.service.OrderService;


@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            Order order = orderService.createOrder(
                orderRequest.getShippingAddress(),
                orderRequest.getRecipient(),
                orderRequest.getPhone(),
                orderRequest.getOrderDate(),
                orderRequest.getPaymentMethod(),
                orderRequest.getStatus(),
                orderRequest.getTotalPrice(),
                orderRequest.getUser(),
                orderRequest.getOrderItems()
            );

            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", order));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrder() {
        try {
            List<OrderDTO> orders = orderService.getAllOrders();
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", orders));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        try {
            OrderDTO order = orderService.getOrder(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", order));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-order-user/{id}")
    public ResponseEntity<?> getOrderByUser(@PathVariable("id") String id) {
        try {
            List<OrderDTO> orders = orderService.getAllOrdersByUser(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS",
                        "data", orders));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String id) {
        try {
            orderService.updateOrder(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cancel-order/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") String id) {
        try {
            orderService.cancelOrder(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) {
        try {
            orderService.deleteOrder(id);
            
            return ResponseEntity.ok(Map.of(
                        "status", "OK",
                        "message", "SUCCESS"));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

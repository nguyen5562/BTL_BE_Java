package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DTO.OrderDTO;
import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.User;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(String shippingAddress, String recipient, String phone, Date orderDate,
            String paymentMethod, int status, int totalPrice, String user, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setShippingAddress(shippingAddress);
        order.setRecipient(recipient);
        order.setPhone(phone);
        order.setOrderDate(orderDate);
        order.setPaymentMethod(paymentMethod);
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        order.setUser(user);

        Order createdOrder = orderRepository.save(order);

        orderItems.stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(createdOrder.getId());
            orderItem.setProduct(item.getProduct());
            orderItem.setName(item.getName());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            orderItem.setTotalPrice(item.getTotalPrice());
            return orderItemRepository.save(orderItem);
        }).collect(Collectors.toList());

        return createdOrder;
    }

    public OrderDTO getOrder(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        Order order = orderOptional.get();
        ObjectId orderId = new ObjectId(order.getId());
        List<OrderItem> orderItems = orderItemRepository.findByOrder(orderId);
        System.out.println(orderItems);

        OrderDTO orderDTO = new OrderDTO(order, orderItems);
        return orderDTO;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            ObjectId orderId = new ObjectId(order.getId());
            List<OrderItem> orderItems = orderItemRepository.findByOrder(orderId);
            User user = userRepository.findById(order.getUser()).orElse(null);
            return new OrderDTO(order, orderItems, user != null ? user.getName() : null);
        }).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByUser(String user) {
        ObjectId userId = new ObjectId(user);
        List<Order> orders = orderRepository.findByUser(userId);

        return orders.stream().map(order -> {
            ObjectId orderId = new ObjectId(order.getId());
            List<OrderItem> orderItems = orderItemRepository.findByOrder(orderId);
            return new OrderDTO(order, orderItems);
        }).collect(Collectors.toList());
    }

    public void updateOrder(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        Order order = orderOptional.get();
        if (order.getStatus() == 1) {
            order.setStatus(2);
        } else if (order.getStatus() == 2) {
            order.setStatus(3);
        }

        orderRepository.save(order);
    }

    public void cancelOrder(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        Order order = orderOptional.get();
        order.setStatus(0);
        orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        ObjectId orderId = new ObjectId(id);
        orderItemRepository.deleteByOrder(orderId);
        orderRepository.deleteById(id);
    }
}

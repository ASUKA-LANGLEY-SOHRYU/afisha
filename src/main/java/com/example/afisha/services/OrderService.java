package com.example.afisha.services;

import com.example.afisha.models.Order;
import com.example.afisha.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {this.orderRepository = orderRepository; }

    public List<Order> getOrdersByUserId(long userId){
        return orderRepository.findOrdersByUserId(userId);
    }
}

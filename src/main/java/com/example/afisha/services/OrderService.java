package com.example.afisha.services;

import com.example.afisha.models.CartDTO;
import com.example.afisha.models.Event;
import com.example.afisha.models.Order;
import com.example.afisha.models.User;
import com.example.afisha.repository.EventRepository;
import com.example.afisha.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final EventRepository eventRepository;

    private final UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, EventRepository eventRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Transactional
    public void makeOrders(List<CartDTO> cartDTOs) {
        for (var cartDTO : cartDTOs) {
            makeOrder(cartDTO);
        }
    }

    @Transactional
    public void makeOrder(CartDTO cartDTO) {
        User currentUser = userService.getCurrentUser();
        Event event = cartDTO.getEvent();
        Order order = Order.builder()
                .user(currentUser)
                .event(event)
                .count(cartDTO.getCount())
                .build();
        orderRepository.save(order);
    }
}

package com.example.afisha.services;

import com.example.afisha.models.CartDTO;
import com.example.afisha.models.Order;
import com.example.afisha.repository.EventRepository;
import com.example.afisha.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final EventRepository eventRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, EventRepository eventRepository) {this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
    }

    public List<Order> getOrdersByUserId(long userId){
        return orderRepository.findOrdersByUserId(userId);
    }

    @Transactional
    public void makeOrders(List<CartDTO> cartDTOs){
        for(var cartDTO : cartDTOs){
            makeOrder(cartDTO);
        }
    }
    @Transactional
    public void makeOrder(CartDTO cartDTO){
        var event = eventRepository.findById(cartDTO.getId());
        var order = Order.builder()
                .count(cartDTO.getCount())
                .build();
    }
}

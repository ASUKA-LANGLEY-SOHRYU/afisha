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

    @Autowired
    public OrderService(OrderRepository orderRepository, EventRepository eventRepository) {this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
    }

    public List<Order> getOrdersByUserId(long userId){
        return orderRepository.findOrdersByUserId(userId);
    }

    @Transactional
    public void makeOrders(User user, List<CartDTO> cartDTOs) {
        for (var cartDTO : cartDTOs) {
            makeOrder(user, cartDTO);
        }
    }

    @Transactional
    public void makeOrder(User user, CartDTO cartDTO) {
        Long eventId = cartDTO.getId();
        if (eventId != null) {
            Optional<Event> eventOptional = eventRepository.findById(eventId);
            if (eventOptional.isPresent()) {
                Event event = eventOptional.get();
                var order = Order.builder()
                        .user(user)
                        .event(event)
                        .count(cartDTO.getCount())
                        .build();
                orderRepository.save(order);
            } else {
                // Обрабатывайте ситуацию, когда мероприятие не найдено
            }
        } else {
            // Обрабатывайте ситуацию, когда идентификатор мероприятия равен null
        }
    }
}

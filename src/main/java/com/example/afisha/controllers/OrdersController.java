package com.example.afisha.controllers;

import com.example.afisha.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersController {
    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping("/order/{userId}")
    public String getMainPage(@PathVariable("userId") long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("userOrders", orderService.getOrdersByUserId(userId));
        model.addAttribute("example", "test");
        return "../frontend/userOrder";
    }
}

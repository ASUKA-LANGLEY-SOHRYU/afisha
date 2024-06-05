package com.example.afisha.controllers;

import com.example.afisha.models.User;
import com.example.afisha.services.OrderService;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrdersController(OrderService orderService, UserService userService)
    {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/order/{userId}")
    public String getMainPage(@PathVariable("userId") long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userOrders", orderService.getOrdersByUserId(userId));
        model.addAttribute("example", "test");
        return "../frontend/userOrder";
    }

    @GetMapping("/orders/my")
    public String getMyOrdersPage(Model model){
        // тут класть все заказы текущего пользователя
        User currentUser = userService.getCurrentUser();
        long userId = currentUser.getId();
        model.addAttribute("userOrders", orderService.getOrdersByUserId(userId));
        return "../frontend/orders";
    }

    @GetMapping("/orders")
    public String getOrdersPage(Model model){
        // тут класть все заказы
        model.addAttribute("userOrders", orderService.getAllOrders());
        return "../frontend/orders";
    }
}

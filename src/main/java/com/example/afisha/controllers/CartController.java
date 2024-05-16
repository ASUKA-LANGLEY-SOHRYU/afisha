package com.example.afisha.controllers;

import com.example.afisha.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    private final OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/cart")
    public String getCartPage(Model model){

        return "../frontend/cart";
    }

    @PostMapping("/cart/add")
    public String addEventToCart(){

        return null;
    }
}

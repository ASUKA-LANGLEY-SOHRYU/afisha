package com.example.afisha.controllers;

import com.example.afisha.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {

    private final OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/cart")
    public String getCartPage(Model model){

        return "../frontend/cart";
    }

    // убрать
    @PostMapping("/addToCart")
    public ResponseEntity<String> addEventToCart(@RequestBody String eventData){
        System.out.println("Event data received: " + eventData);
        return ResponseEntity.ok("Event added to cart successfully");
    }
}

package com.example.afisha.controllers;

import com.example.afisha.models.CartDTO;
import com.example.afisha.models.User;
import com.example.afisha.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping
    public String getCartPage(Model model){

        return "../frontend/cart";
    }

    @PostMapping("/add")
    public String addEventToCart(){

        return null;
    }

    @PostMapping("/purchase")
    public String purchaseCart(@RequestBody String cartDTOs, Authentication authentication){
        System.out.println(((User) authentication.getPrincipal()).getEmail());
        //orderService.makeOrders(cartDTOs);
        return "redirect:/cart";
    }
}

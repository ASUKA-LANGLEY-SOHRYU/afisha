package com.example.afisha.controllers;

import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.getUsersData());
        model.addAttribute("example", "test");
        return "../frontend/users";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        model.addAttribute("example2", "test2");
        model.addAttribute("userEvents", userService.getUserOrders());
        return "../frontend/profile";
    }
}

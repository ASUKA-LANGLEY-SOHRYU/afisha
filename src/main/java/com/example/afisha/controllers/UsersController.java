package com.example.afisha.controllers;

import com.example.afisha.models.User;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/users/me")
    public String getMyProfilePage(Model model, Authentication authentication){
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("example2", "test2");
        model.addAttribute("userEvents", userService.getUserOrders());
        return "../frontend/profile";
    }

    @PostMapping("/users/me")
    public String editMe(Model model, Authentication authentication){
        //userService.editMe();
        return "../frontend/profile";
    }

    @GetMapping("/users/{id}")
    public String getProfilePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("example2", "test2");
        model.addAttribute("userEvents", userService.getUserOrders());
        return "../frontend/profile";
    }
}

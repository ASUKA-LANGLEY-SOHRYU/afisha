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
    public String getMainPage(Model model){
        model.addAttribute("users", userService.getUsersData());
        model.addAttribute("example", "test");
        return "../frontend/users";
    }
}

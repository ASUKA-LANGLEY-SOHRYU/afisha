package com.example.afisha.controllers;

import com.example.afisha.models.User;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.getUsersData());
        model.addAttribute("example", "test");
        return "../frontend/users";
    }

    @GetMapping("/me")
    public String getMyProfilePage(Model model, Authentication authentication){
        model.addAttribute("fields", Arrays.asList("lastName", "firstName", "patronymic", "phoneNumber", "birthDate", "email", "password"));
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("userEvents", userService.getUserOrders());
        return "../frontend/profile";
    }

    @GetMapping("/{id}")
    public String getProfilePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("fields", Arrays.asList("lastName", "firstName", "patronymic", "phoneNumber", "birthDate", "email"));
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("userEvents", userService.getUserOrders());
        return "../frontend/profile";
    }

    @GetMapping("/edit/{id}")
    public String getEditProfilePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("fields", Arrays.asList("lastName", "firstName", "patronymic", "phoneNumber", "birthDate", "email", "password"));
        model.addAttribute("editMode", true);
        model.addAttribute("user", userService.getUserById(id));
        return "../frontend/profile";
    }
}

package com.example.afisha.controllers;

import com.example.afisha.models.User;
import com.example.afisha.models.dto.UserEditDTO;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.getUsersData());
        model.addAttribute("example", "test");
        return "../frontend/users";
    }

    @GetMapping("/me")
    public String getMyProfilePage(Model model, Authentication authentication){
        model.addAttribute("user", userService.getCurrentUser());
        return "../frontend/profile";
    }

    @GetMapping("/{id}")
    public String getProfilePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "../frontend/profile";
    }

    @GetMapping("/edit/{id}")
    public String getEditProfilePage(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "../frontend/editUser";
    }

    @PostMapping("/edit/{id}")
    public String editUser(Model model, @ModelAttribute UserEditDTO userEditDTO, @PathVariable("id") Long id){
        userService.editUserById(id, userEditDTO);
        model.addAttribute("message", "Пользователь успешно отредактирован!");
        return ("redirect:/users/" + id);
    }
}

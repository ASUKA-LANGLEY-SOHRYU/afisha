package com.example.afisha.controllers;

import com.example.afisha.models.User;
import com.example.afisha.services.auth.AuthenticationService;
import com.example.afisha.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserValidator userValidator;

    public AuthController(AuthenticationService authenticationService, UserValidator userValidator) {
        this.authenticationService = authenticationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") User user,
                           BindingResult bindingResult){
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors())
            return "auth/registration";

        authenticationService.register(user);
        return "redirect:/auth/login";
    }
}

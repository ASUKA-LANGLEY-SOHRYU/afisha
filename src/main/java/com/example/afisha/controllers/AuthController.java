package com.example.afisha.controllers;

import com.example.afisha.models.form.UserForm;
import com.example.afisha.services.auth.AuthenticationService;
import com.example.afisha.util.UserFormMapper;
import com.example.afisha.util.UserFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserFormValidator userFormValidator;

    private final UserFormMapper userFormMapper;

    public AuthController(AuthenticationService authenticationService, UserFormValidator userValidator, UserFormMapper userFormMapper) {
        this.authenticationService = authenticationService;
        this.userFormValidator = userValidator;
        this.userFormMapper = userFormMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") UserForm user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") UserForm user,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (user.getIsOrganizer() == null) {
            user.setIsOrganizer(false);
        }

        userFormValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors())
            return "auth/registration";

        authenticationService.register(userFormMapper.map(user));

        redirectAttributes.addFlashAttribute("registrationSuccess", true);

        return "redirect:/auth/login";
    }
}

package com.example.afisha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(){
        return "../frontend/index";
    }

    @GetMapping("/hello")
    public String getHelloPage(){
        return "../frontend/hello";
    }

}

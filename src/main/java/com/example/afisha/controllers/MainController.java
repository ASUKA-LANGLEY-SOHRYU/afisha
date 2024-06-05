package com.example.afisha.controllers;

import com.example.afisha.repository.specification.EventFilter;
import com.example.afisha.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class MainController {

    private final EventService eventService;

    @Autowired
    public MainController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/hello")
    public String getHelloPage(){
        return "../frontend/hello";
    }

    @GetMapping("/")
    public String getMainPage(Model model,
                              @ModelAttribute EventFilter eventFilter){
        model.addAttribute("events", eventService.findAll(eventFilter));
        return "../frontend/index";
    }

    @GetMapping("/error_role")
    public String getErrorRolePage(Model model){
        return "../frontend/error_role";
    }
}

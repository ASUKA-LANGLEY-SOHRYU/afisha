package com.example.afisha.controllers;

import com.example.afisha.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                              @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(required = false) String sortFieldName,
                              @RequestParam(required = false) String sortDirection){
        model.addAttribute("events", eventService.findAll(page, size, sortFieldName, sortDirection));
        return "../frontend/index";
    }

    @GetMapping("/error_role")
    public String getErrorRolePage(Model model){
        return "../frontend/error_role";
    }
}

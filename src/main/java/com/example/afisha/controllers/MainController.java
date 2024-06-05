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
    public String getMainPage(Model model){
        model.addAttribute("events", eventService.getEventsData());
        model.addAttribute("example", "test");
        return "../frontend/index";
    }

    @GetMapping("/sortEventsByDate")
    public String sortEventsByDate(Model model){
        model.addAttribute("events", eventService.getEventsSortedByDate());
        return "../frontend/index";
    }

    @GetMapping("/error_role")
    public String getErrorRolePage(Model model){
        return "../frontend/error_role";
    }
}

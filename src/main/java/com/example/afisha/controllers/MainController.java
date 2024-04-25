package com.example.afisha.controllers;

import com.example.afisha.models.Event;
import com.example.afisha.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainController {

    private final EventService eventService;

    @Autowired
    public MainController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("events", eventService.getEventsData());
        model.addAttribute("example", "test");
        return "../frontend/index";
    }

    @GetMapping("/hello")
    public String getHelloPage(){
        return "../frontend/hello";
    }

    @ModelAttribute("events")
    public List<Event> getAllEvents() { return eventService.getEventsData(); }

}

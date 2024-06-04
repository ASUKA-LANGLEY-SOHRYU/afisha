package com.example.afisha.controllers;

import com.example.afisha.models.Event;
import com.example.afisha.models.User;
import com.example.afisha.services.EventService;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventsController {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventsController(EventService eventService, UserService userService){
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/addEvent")
    public String showAddEvent(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("example", "test");
        return "../frontend/addEvent";
    }

    @PostMapping("/addEvent")
    public String createEvent(@ModelAttribute("event") Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "../frontend/addEvent";
        }

        User currentUser = (User) userService.getCurrentUser();
        event.setOrganization(currentUser);
        eventService.createEvent(event);
        return "redirect:/";
    }
}

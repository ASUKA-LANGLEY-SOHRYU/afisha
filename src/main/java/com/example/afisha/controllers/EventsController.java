package com.example.afisha.controllers;

import com.example.afisha.models.Event;
import com.example.afisha.models.User;
import com.example.afisha.services.EventService;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventsController(EventService eventService, UserService userService){
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String showAddEvent(Model model){
        model.addAttribute("event", new Event());
        return "../frontend/addEvent";
    }

    @PostMapping("/add")
    public String createEvent(@ModelAttribute("event") Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "../frontend/addEvent";
        }

        User currentUser = userService.getCurrentUser();
        long usId = currentUser.getId();
        event.setOrganization(currentUser);
        event.setOrders(new ArrayList<>());
        eventService.createEvent(event);
        return "redirect:/";
    }

    // ивенты организатора
    @GetMapping("/my")
    public String myEvents(Authentication authentication, Model model){
        model.addAttribute("events", eventService.getAllOrganizerEvents(authentication));
        return "../frontend/myEvents";
    }
}

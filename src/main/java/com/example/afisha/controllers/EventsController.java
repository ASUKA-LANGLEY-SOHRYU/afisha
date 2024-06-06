package com.example.afisha.controllers;

import com.example.afisha.models.Event;
import com.example.afisha.models.User;
import com.example.afisha.models.dto.UserEditDTO;
import com.example.afisha.services.EventService;
import com.example.afisha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventsController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String showAddEvent(Model model) {
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
    public String myEvents(Authentication authentication, Model model) {
        model.addAttribute("events", eventService.getAllOrganizerEvents(authentication));
        return "../frontend/myEvents";
    }

    @GetMapping("/{id}")
    public String getEventPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("event", eventService.getEventById(id));
        return "../frontend/event";
    }

    @GetMapping("/edit/{id}")
    public String getEditEventPage(Model model, @PathVariable("id") Long id){
        var user = userService.getCurrentUser();
        if (user.getId().equals(id))
            return "redirect:/users/edit/me";
        model.addAttribute("user", userService.getUserById(id));
        return "../frontend/editUser";
    }

    @PostMapping("/edit/{id}")
    public String editEvent(Model model, @ModelAttribute UserEditDTO userEditDTO, @PathVariable("id") Long id){
        userService.editUserById(id, userEditDTO);
        model.addAttribute("message", "Пользователь успешно отредактирован!");
        var user = userService.getCurrentUser();
        if (user.getId().equals(id))
            return "redirect:/users/me";
        return ("redirect:/users/" + id);
    }
}

package com.example.afisha.controllers;

import com.example.afisha.models.dto.EventDTO;
import com.example.afisha.repository.specification.EventFilter;
import com.example.afisha.services.EventService;
import com.example.afisha.util.EventDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final EventService eventService;
    private final EventDTOMapper eventDTOMapper;

    @Autowired
    public MainController(EventService eventService, EventDTOMapper eventDTOMapper) {
        this.eventService = eventService;
        this.eventDTOMapper = eventDTOMapper;
    }

    @GetMapping("/")
    public String getMainPage(Model model, @ModelAttribute EventFilter eventFilter){
        model.addAttribute("events", eventService.findAll(eventFilter).stream()
                .map(eventDTOMapper::map).toList());
        model.addAttribute("filter", new EventFilter());
        return "../frontend/index";
    }

    @GetMapping("/error_role")
    public String getErrorRolePage(Model model){
        return "../frontend/error_role";
    }
}

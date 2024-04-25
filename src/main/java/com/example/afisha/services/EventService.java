package com.example.afisha.services;

import com.example.afisha.models.Event;
import com.example.afisha.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEventsData(){
        return eventRepository.findAll();
    }

    public List<Event> getEventsSortedByDate() { return eventRepository.findAll(Sort.by("DateTime").ascending()); }
}

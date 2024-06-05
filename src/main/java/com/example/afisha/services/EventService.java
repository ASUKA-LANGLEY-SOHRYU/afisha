package com.example.afisha.services;

import com.example.afisha.models.Event;
import com.example.afisha.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Event> findAll(Integer page, Integer size, String sortFieldName, String sortDirection){
        Pageable pageable;
        if (sortFieldName == null)
            pageable = PageRequest.of(page, size);
        else {
            if (!sortDirection.equals("asc") && !sortDirection.equals("desc"))
                sortDirection = "asc";
            var sort = Sort.by(Sort.Direction.fromString(sortDirection), sortFieldName);
            pageable = PageRequest.of(page, size, sort);
        }
        return eventRepository.findAll(pageable);
    }

    public List<Event> getEventsSortedByDate() { return eventRepository.findAll(Sort.by("dateTime").ascending()); }

    public void createEvent(Event event){
        eventRepository.save(event);
    }
}

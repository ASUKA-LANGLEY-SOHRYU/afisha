package com.example.afisha.services;

import com.example.afisha.models.Event;
import com.example.afisha.repository.EventRepository;
import com.example.afisha.repository.specification.EventFilter;
import com.example.afisha.repository.specification.EventSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Page<Event> findAll(EventFilter eventFilter){
        int page = 0;
        int size = 10;
        if (eventFilter.getPage() != null)
            page = eventFilter.getPage();
        if (eventFilter.getSize() != null)
            size = eventFilter.getSize();
        Pageable pageable;
        var spec = EventSpecification.getEventSpecification(eventFilter.toEventSpecificationFilter());
        if (eventFilter.getSortFieldName() == null)
            pageable = PageRequest.of(page, size);
        else {
            if (!eventFilter.getSortDirection().equals("asc") && !eventFilter.getSortDirection().equals("desc"))
                eventFilter.setSortDirection("asc");
            var sort = Sort.by(Sort.Direction.fromString(eventFilter.getSortDirection()), eventFilter.getSortFieldName());
            pageable = PageRequest.of(page, size, sort);
        }
        return eventRepository.findAll(spec, pageable);
    }

    public List<Event> getEventsSortedByDate() { return eventRepository.findAll(Sort.by("dateTime").ascending()); }

    public void createEvent(Event event){
        eventRepository.save(event);
    }
}

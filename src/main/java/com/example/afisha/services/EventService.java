package com.example.afisha.services;

import com.example.afisha.models.Event;
import com.example.afisha.models.User;
import com.example.afisha.models.dto.EventDTO;
import com.example.afisha.models.dto.EventEditDTO;
import com.example.afisha.repository.EventRepository;
import com.example.afisha.repository.specification.EventFilter;
import com.example.afisha.repository.specification.EventSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEventsData() {
        return eventRepository.findAll();
    }

    public Page<Event> findAll(EventFilter eventFilter) {
        var sortDirections = List.of("asc", "desc");
        var sortFields = List.of("name", "dateTime", "price");
        int page = 0;
        int size = 12;
        if (eventFilter.getPage() != null)
            page = eventFilter.getPage();
        if (eventFilter.getSize() != null)
            size = eventFilter.getSize();
        Pageable pageable;
        var spec = EventSpecification.getEventSpecification(eventFilter.toEventSpecificationFilter());
        if (eventFilter.getSortFieldName() == null)
            pageable = PageRequest.of(page, size);
        else {
            if (!sortDirections.contains(eventFilter.getSortDirection()))
                eventFilter.setSortDirection("asc");
            if (!sortFields.contains(eventFilter.getSortFieldName()))
                eventFilter.setSortFieldName("dateTime");
            var sort = Sort.by(Sort.Direction.fromString(eventFilter.getSortDirection()), eventFilter.getSortFieldName());
            pageable = PageRequest.of(page, size, sort);
        }
        return eventRepository.findAll(spec, pageable);
    }

    public List<Event> getAllOrganizerEvents(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return eventRepository.findAllByOrganizationId(user.getId());
    }

    public List<Event> getEventsSortedByDate() {
        return eventRepository.findAll(Sort.by("dateTime").ascending());
    }

    public Event getEventById(long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void edit(EventEditDTO eventEditDTO, Long eventId){
        var event = eventRepository.findById(eventId).orElseThrow();

        if(eventEditDTO.getName() != null)
            event.setName(eventEditDTO.getName());
        if(eventEditDTO.getPrice() != null)
            event.setPrice(eventEditDTO.getPrice());
        if(eventEditDTO.getDateTime() != null)
            event.setDateTime(eventEditDTO.getDateTime());

        eventRepository.save(event);
    }
}

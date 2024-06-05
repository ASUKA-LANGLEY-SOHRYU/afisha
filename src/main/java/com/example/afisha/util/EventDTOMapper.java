package com.example.afisha.util;

import com.example.afisha.models.Event;
import com.example.afisha.models.dto.EventDTO;
import org.springframework.stereotype.Component;

@Component
public class EventDTOMapper implements Mapper<EventDTO, Event>{
    @Override
    public EventDTO map(Event source) {
        return EventDTO.builder()
                .id(source.getId())
                .price(source.getPrice())
                .name(source.getName())
                .dateTime(source.getDateTime())
                .build();
    }
}

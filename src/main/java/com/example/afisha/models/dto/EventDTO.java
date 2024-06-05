package com.example.afisha.models.dto;

import com.example.afisha.models.Event;
import com.example.afisha.util.Mapper;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDTO {
    private long id;
    private Integer price;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

}

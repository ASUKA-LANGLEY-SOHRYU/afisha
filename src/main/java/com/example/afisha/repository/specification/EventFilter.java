package com.example.afisha.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFilter {
    private Integer page;
    private Integer size;
    private String sortFieldName;
    private String sortDirection;
    private String name;
    private Integer priceFrom;
    private Integer priceTo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //2023-10-12T14:30:00
    private LocalDateTime dateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //2023-10-12T14:30:00
    private LocalDateTime dateTo;

    public EventSpecificationFilter toEventSpecificationFilter(){
        return new EventSpecificationFilter(name, priceFrom, priceTo, dateFrom, dateTo);
    }
}

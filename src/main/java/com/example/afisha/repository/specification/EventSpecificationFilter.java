package com.example.afisha.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class EventSpecificationFilter {
    private String name;
    private Integer priceFrom;
    private Integer priceTo;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
}

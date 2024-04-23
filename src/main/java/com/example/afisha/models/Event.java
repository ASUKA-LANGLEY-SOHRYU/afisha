package com.example.afisha.models;

import lombok.*;

import java.util.*;

// поля: id, price, dateTime, organization

@Setter
@Getter
public class Event {
    private Long id;

    private Integer price;

    private Date DateTime;

    private String Organization;
}

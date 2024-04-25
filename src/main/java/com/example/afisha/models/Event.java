package com.example.afisha.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer price;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    private String organization;

    @OneToMany(mappedBy = "event")
    private List<Order> orders;

}

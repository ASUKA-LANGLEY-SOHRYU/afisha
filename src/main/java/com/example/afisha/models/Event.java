package com.example.afisha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @JsonIgnore
    private User organization;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Order> orders;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", organization='" + organization + '\'' +
                '}';
    }
}
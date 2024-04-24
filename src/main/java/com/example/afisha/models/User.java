package com.example.afisha.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String phoneNumber;

    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    //TODO: добавить после подключения Spring Security
    //private Role role;
}

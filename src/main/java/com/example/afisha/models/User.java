package com.example.afisha.models;

import lombok.*;
import java.util.*;

// Поля: id, lastName, firstName, middleName, phoneNumber, email, birthDate, role(user/organizer/admin)

@Setter
@Getter
public class User {
    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String phoneNumber;

    private String email;

    private Date birthDate;

    private Role role;
}

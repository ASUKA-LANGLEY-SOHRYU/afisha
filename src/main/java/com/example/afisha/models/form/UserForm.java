package com.example.afisha.models.form;

import com.example.afisha.models.Order;
import com.example.afisha.models.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UserForm{

    private String lastName;

    private String firstName;

    private String patronymic;

    private String phoneNumber;

    private String email;

    private String password;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;

    private Boolean isOrganizer;
}

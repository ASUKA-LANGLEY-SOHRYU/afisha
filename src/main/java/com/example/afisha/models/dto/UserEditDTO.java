package com.example.afisha.models.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserEditDTO {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
}

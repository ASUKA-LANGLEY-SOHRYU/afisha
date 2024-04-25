package com.example.afisha.util;

import com.example.afisha.models.Role;
import com.example.afisha.models.User;
import com.example.afisha.models.form.UserForm;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class UserFormMapper implements Mapper<User, UserForm>{

    @Override
    public User map(UserForm source) {
        var roles = new HashSet<Role>(2);
        roles.add(Role.USER);
        if (source.getIsOrganizer())
            roles.add(Role.ORGANIZER);
        return User.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .patronymic(source.getPatronymic())
                .email(source.getEmail())
                .birthDate(source.getBirthDate())
                .phoneNumber(source.getPhoneNumber())
                .password(source.getPassword())
                .roles(roles)
                .build();
    }
}

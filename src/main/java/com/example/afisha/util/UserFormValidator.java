package com.example.afisha.util;

import com.example.afisha.models.form.UserForm;
import com.example.afisha.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {

    private final UserRepository userRepository;

    public UserFormValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm user = (UserForm) target;
        if (userRepository.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "", "Пользователь с такми email уже существует.");
        //TODO: Добавить полную валидацию
    }
}

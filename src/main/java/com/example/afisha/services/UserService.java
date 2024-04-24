package com.example.afisha.services;

import com.example.afisha.models.User;
import com.example.afisha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByFirstName(String firstname) {
        var user = userRepository.findByFirstName(firstname);
        if (user != null)
            System.out.println(user);
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

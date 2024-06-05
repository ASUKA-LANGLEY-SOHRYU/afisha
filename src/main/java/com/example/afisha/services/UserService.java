package com.example.afisha.services;

import com.example.afisha.models.Event;
import com.example.afisha.models.Order;
import com.example.afisha.models.User;
import com.example.afisha.models.dto.UserEditDTO;
import com.example.afisha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersData() {
        return userRepository.findAll();
    }

    public User findByFirstName(String firstname) {
        var user = userRepository.findByFirstName(firstname);
        if (user != null)
            System.out.println(user);
        return user;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public List<Order> getUserOrders() {
        //List<Order> orders = ;

        return null;
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }

    public void editMe(UserEditDTO userEdit){
        var user = getCurrentUser();
        if(userEdit.getFirstName() != null)
            user.setFirstName(userEdit.getFirstName());
        if(userEdit.getPatronymic() != null)
            user.setPatronymic(userEdit.getPatronymic());
        if(userEdit.getLastName() != null)
            user.setLastName(userEdit.getLastName());
        if(userEdit.getEmail() != null)
            user.setEmail(userEdit.getEmail());
        if(userEdit.getPhoneNumber() != null)
            user.setPhoneNumber(userEdit.getPhoneNumber());
        if(userEdit.getBirthDate() != null)
            user.setBirthDate(Timestamp.from(Instant.from(userEdit.getBirthDate())));
        userRepository.save(user);
    }
}

package com.example.afisha.repository;

import com.example.afisha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String firstName);

    User findByEmail(String email);
}

package com.example.user_service.repository;

import com.example.user_service.model.User;
import com.example.user_service.model.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserloginRepo extends JpaRepository<Userlogin, Long> {
    Optional<Userlogin> findbyEmail(String email);
}

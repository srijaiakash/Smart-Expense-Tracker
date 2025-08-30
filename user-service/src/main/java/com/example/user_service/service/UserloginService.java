package com.example.user_service.service;

import com.example.user_service.UserDTO.RegisterRequest;
import com.example.user_service.model.User;
import com.example.user_service.model.Userlogin;
import com.example.user_service.repository.UserloginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserloginService {
    @Autowired
    private UserloginRepo userloginRepo;
    @PostMapping
    public Userlogin registerUser(Userlogin user){
        return userloginRepo.save(user);
    }
    public Userlogin findById(Long id) {
        return userloginRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


}

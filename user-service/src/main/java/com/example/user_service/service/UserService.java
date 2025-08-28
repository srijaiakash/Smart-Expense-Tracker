package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.user_service.UserDTO.LoginRequest;
import com.example.user_service.UserDTO.RegisterRequest;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 public String register(RegisterRequest request) {
	        if (userRepository.existsByUsername(request.getUsername())) {
	            return "Username already exists!";
	        }

	        User user = User.builder()
	                .username(request.getUsername())
	                .password(passwordEncoder.encode(request.getPassword()))
	                .email(request.getEmail())
	                .role("ROLE_USER")
	                .build();

	        userRepository.save(user);
	        return "User registered successfully!";
	    }

	    public String login(LoginRequest request) {
	        return userRepository.findByUsername(request.getUsername())
	                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
	                .map(user -> "Login successful")
	                .orElse("Invalid credentials");
	    }

}

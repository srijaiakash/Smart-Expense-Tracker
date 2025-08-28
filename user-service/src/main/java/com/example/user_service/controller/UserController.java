package com.example.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.UserDTO.LoginRequest;
import com.example.user_service.UserDTO.RegisterRequest;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("api/auth")
public class UserController {
	@Autowired
	private UserService userservice;
	
	@PostMapping("/register")
	
	public ResponseEntity<String> register(@RequestBody RegisterRequest request)
	{
		String response = userservice.register(request);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request)
	{
		String response = userservice.login(request);
		return ResponseEntity.ok(response);
		
	}

}

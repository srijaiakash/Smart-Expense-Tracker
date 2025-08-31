package com.example.user_service.controller;

import com.example.user_service.model.User;
import com.example.user_service.model.Userlogin;
import com.example.user_service.repository.UserloginRepo;
import com.example.user_service.service.UserloginService;
import com.example.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserloginRepo repo;
    @Autowired
    private UserloginService userloginservice;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody Map<String, String> body){
        String email=body.get("email");
        String password=body.get("password");
        password=passwordEncoder.encode(password);
        if(repo.findbyEmail(email).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exist");
        }
        else{
            userloginservice.registerUser(Userlogin.builder().email(email).password(password).build());
            return ResponseEntity.status(HttpStatus.CREATED).body("Register successfully");

        }


    }
    @PostMapping("/login")
    public ResponseEntity<?> userlogin(@RequestBody Map<String, String> body){
        String email=body.get("email");
        String password=body.get("password");
        var val=repo.findbyEmail(email);
        if(repo.findbyEmail(email).isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please go and register");
        }
        Userlogin user=val.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect valid User");

        }
        String token=jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));


    }

}

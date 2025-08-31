package com.example.user_service.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET="Spring Boot makes it easy to create stand-alone, production-grade applications. It reduces configuration effort and integrates with databases, security, and REST APIs efficiently.";
    private final long EXPIRATION=1000*30;
    private final Key secretKey= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String email){
       return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION)).signWith(secretKey, SignatureAlgorithm.ES256).compact();
    }
    public String Extractemail(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validatejwttoken(String token){
        try{
            Extractemail(token);
            return true;
        }catch(JwtException exception){
            return false;
        }
    }


}

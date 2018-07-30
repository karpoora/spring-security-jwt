package com.plantwoo.security.service;

import com.plantwoo.security.model.User;
import com.plantwoo.security.token.TokenHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    TokenHandler tokenHandler;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String validateCredential(User user) {
        if(validateUser(user)){
            return generateAuthToken(user);
        }
        return null;
    }

    private String generateAuthToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("userId", user.getUserId());
        claims.put("role", user.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    private boolean validateUser(User user) {
        return true;
    }
}

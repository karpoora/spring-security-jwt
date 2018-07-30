package com.plantwoo.security.token;

import com.plantwoo.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenHandler {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public User validateToken(String token) {
        Claims tokenBody = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        User user = new User();
        user.setUserName(tokenBody.getSubject());
        user.setRole((String) tokenBody.get("role"));
        user.setUserId((String) tokenBody.get("userId"));
        return user;
    }

}

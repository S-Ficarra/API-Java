package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MaCléSecrèteTrèsLonguePourSécurité";
    private static final long EXPIRATION_TIME = 3600000; // 1 heure

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private final AuthService authService;

    @Autowired
    public JwtUtil(AuthService authService) {
        this.authService = authService;
    }

    public String generateToken(String email, String password) {
        // Vérifie que le nom d'utilisateur et le mot de passe sont valides
        if (authService.authenticate(email, password)) {
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}

package com.example.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.DemoApplication;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    // private String ACCESS_TOKEN = "access_token";
    // private String REFRESH_TOKEN = "refresh_token";

    public String generalAccessToken(String id, Boolean isAdmin) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + (1000 * 60 * 60); // Token expires in 1 hour

        String accessToken = Jwts.builder()
                .claim("id", id)
                .claim("isAdmin", isAdmin)
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(expirationTimeMillis))
                .signWith(DemoApplication.key)
                .compact();

        return accessToken;
    }

    public String generalRefreshToken(String id, Boolean isAdmin) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + (1000 * 60 * 60 * 24 * 2); // Token expires in 1 hour

        String refreshToken = Jwts.builder()
                .claim("id", id)
                .claim("isAdmin", isAdmin)
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(expirationTimeMillis))
                .signWith(DemoApplication.key)
                .compact();

        return refreshToken;
    }
}

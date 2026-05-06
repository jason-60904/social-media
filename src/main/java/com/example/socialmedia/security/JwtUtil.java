package com.example.socialmedia.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes());

    private static final long EXPIRATION = 1000 * 60 * 60; // 1小時

    public static String generateToken(String phone) {
        return Jwts.builder()
                .setSubject(phone)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    public static String getPhone(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validate(String token) {
        try {
            getPhone(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
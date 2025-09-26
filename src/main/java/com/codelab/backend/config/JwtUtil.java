package com.codelab.backend.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "Launch CopyQ from the application menu.\n" +
            "Set a shortcut:";
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());
    private final long expirationMillis = 1000 * 60 * 60 * 24;

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuer("codelab-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key) .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {

            System.out.println("Invalid JWT Token: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT Token: " + ex.getMessage());
        }
        return false;

    }
    public String getRoleFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("role", String.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }}

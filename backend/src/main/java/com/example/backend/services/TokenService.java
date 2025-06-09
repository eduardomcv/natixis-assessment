package com.example.backend.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.backend.models.User;

@Service
public class TokenService {
    // Ideally this should stored securely, e.g. in an environment variable or a
    // secure vault
    private final String secret = "my-secret-key";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("backend-application")
                    .withSubject(user.getUsername())
                    .withExpiresAt(
                            LocalDateTime.now()
                                    .plusHours(1) // Set token expiration to 1 hour from now
                                    .toInstant(ZoneOffset.UTC))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("backend-application")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return ""; // Return an empty string if the token is invalid
        }
    }
}

package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.AuthenticationDTO;
import com.example.backend.models.ErrorPayload;
import com.example.backend.models.User;
import com.example.backend.services.AuthenticationService;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationController() {
        this.authenticationService = new AuthenticationService();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AuthenticationDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.username(),
                data.password());

        this.authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Validated AuthenticationDTO data) {
        String username = data.username();

        if (this.authenticationService.userExists(username)) {
            ErrorPayload payload = new ErrorPayload("User already exists");

            return ResponseEntity.badRequest().body(payload);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(username, encryptedPassword);

        this.authenticationService.registerUser(newUser);

        return ResponseEntity.ok().build();
    }
}

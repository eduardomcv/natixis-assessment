package com.example.backend.services;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.models.User;
import com.example.backend.models.UserRole;

@Service
public class AuthenticationService implements UserDetailsService {
    private final HashMap<String, User> users;

    public AuthenticationService() {
        this.users = new HashMap<>();

        // Register a default admin user (with a *very* safe password)
        User adminUser = new User("admin", new BCryptPasswordEncoder().encode("12345"), UserRole.ADMIN);

        this.registerUser(adminUser);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public void registerUser(User user) {
        String username = user.getUsername();

        if (this.userExists(username)) {
            throw new IllegalArgumentException("User already exists with username: " + username);
        }

        users.put(username, user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.userExists(username)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return users.get(username);
    }
}

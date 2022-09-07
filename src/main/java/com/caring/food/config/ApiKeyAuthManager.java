package com.caring.food.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthManager implements AuthenticationManager {
    private final String API_KEY = "7LyA7Ja066eB7JeQ7ISc7ZaJ67O17ZWY6rKM6rCc67Cc7ZWY7Iuk64+Z66OM6rCc67Cc7J6Q66W86rWs7ZWp64uI64ukCg==";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        if (!API_KEY.equals(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

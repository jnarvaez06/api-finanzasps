package com.jn.api_gastos.auth;

import com.jn.api_gastos.config.exception.CustomMessageException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomMessageException("No user information is received");
        }

        return authentication.getName();
    }
}

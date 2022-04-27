package org.jaksa.config;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class KeycloakContextHolder {
    public static Optional<KeycloakSecurityContext> getKeycloakPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof KeycloakPrincipal) {
                return Optional.of(((KeycloakPrincipal<?>) principal).getKeycloakSecurityContext());
            }
        }
        return Optional.empty();
    }

}

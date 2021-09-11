package com.swithExample.driven.models.auditable;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String>, Serializable {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.of(name);
        }
        return Optional.empty();
    }
}

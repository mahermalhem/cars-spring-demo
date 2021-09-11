package com.swithExample.driven.common.enums;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.swithExample.driven.common.enums.UserPermission.USER_READ;
import static com.swithExample.driven.common.enums.UserPermission.USER_WRITE;

@Getter
public enum UserRole {
    USER(Sets.newHashSet()), ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<UserPermission> authorities;

    UserRole(Set<UserPermission> authorities) {
        this.authorities = authorities;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}

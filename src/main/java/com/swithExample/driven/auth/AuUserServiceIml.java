package com.swithExample.driven.auth;

import com.swithExample.driven.common.exception.errors.MyNotFoundException;
import com.swithExample.driven.models.User;
import com.swithExample.driven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("auth")
public class AuUserServiceIml implements AuthUserService {

    private final UserService userService;

    @Autowired
    public AuUserServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthUser selectApplicationUserByUsername(String username) {

        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null)
            throw new MyNotFoundException(String.format("User %s not found", username));
        return new AuthUser(
                userByUsername.getUsername(),
                userByUsername.getPassword(),
                userByUsername.getRole().getAuthorities(),
                true, userByUsername.isNotLocked(), userByUsername.isActive(), true);
    }
}

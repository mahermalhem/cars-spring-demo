package com.swithExample.driven.auth;

import com.swithExample.driven.models.User;
import com.swithExample.driven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ApplicationUserService implements UserDetailsService {

    private final AuthUserService authUserService;
    private final UserService userService;

    @Autowired
    public ApplicationUserService(@Qualifier("auth") AuthUserService authUserService,

                                  UserService userService
    ) {
        this.authUserService = authUserService;
        this.userService = userService;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AuthUser authUser = authUserService.selectApplicationUserByUsername(username);
        return authUserService.selectApplicationUserByUsername(username);
    }


}

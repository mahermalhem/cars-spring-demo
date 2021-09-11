package com.swithExample.driven.controller;


import com.swithExample.driven.auth.AuthUser;
import com.swithExample.driven.common.enums.AppStatus;
import com.swithExample.driven.common.exception.anotationvalidation.BadRequestException;
import com.swithExample.driven.common.exception.errors.MyBadRequestException;
import com.swithExample.driven.common.exception.errors.MyExistedException;
import com.swithExample.driven.common.exception.errors.MyNotFoundException;
import com.swithExample.driven.controller.helper.UserHelper;
import com.swithExample.driven.controller.helper.Validator;
import com.swithExample.driven.controller.request.AuthUserRequest;
import com.swithExample.driven.controller.request.CreateUserRequest;
import com.swithExample.driven.controller.request.UpdateUserRequest;
import com.swithExample.driven.controller.response.AuthResponse;
import com.swithExample.driven.controller.response.RestApiResponse;
import com.swithExample.driven.jwt.JwtTokenProvider;
import com.swithExample.driven.models.User;
import com.swithExample.driven.services.UserService;
import com.swithExample.driven.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final ResponseUtil responseUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserHelper userHelper;
    @Autowired
    private final AuthenticationManager authenticationManager;


    public AuthController(UserService userService, ResponseUtil responseUtil, JwtTokenProvider jwtTokenProvider, UserHelper userHelper, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.responseUtil = responseUtil;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userHelper = userHelper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<RestApiResponse> authenticateUser(@Valid @RequestBody AuthUserRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthUser principal = (AuthUser) authentication.getPrincipal();

            String token = jwtTokenProvider.generateToken(principal);

            AuthResponse authResponse = new AuthResponse();

            authResponse.setToken(token);
            authResponse.setUsername(principal.getUsername());

            return responseUtil.successResponse(authResponse);
        } catch (MyBadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RestApiResponse> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return responseUtil.successResponse(allUsers);
    }

    // Add user
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity<RestApiResponse> addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        try {
            User oneByUsername = userService.getUserByUsername(createUserRequest.getUsername().trim());
            if (oneByUsername != null)
                throw new MyExistedException(String.format("User %s already in use", createUserRequest.getUsername()));
            Validator.validateEmail(createUserRequest.getEmail());

            User oneByEmail = userService.getUserByEmail(createUserRequest.getEmail());
            if (oneByEmail != null)
                throw new MyExistedException(String.format("User %s already in use", createUserRequest.getEmail()));

            User user = userHelper.createUser(createUserRequest);

            userService.saveUser(user);

            return responseUtil.successResponse(user);

        } catch (BadRequestException exception) {
            throw new RuntimeException(exception);
        }
    }

    // Update user
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, path = "{username}")
    public ResponseEntity<RestApiResponse> updateUser(@Valid @PathVariable String username, @RequestBody UpdateUserRequest userRequest) {
        User user;

        if (Validator.isEmailFormat(username)) {
            user = userService.getUserByEmail(username);
        } else {
            user = userService.getUserByUsername(username);
        }
        userHelper.notFoundUser(user, username);

        userHelper.updateUser(user, userRequest);

        userService.saveUser(user);

        return responseUtil.successResponse(user);
    }

    //Delete user
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, path = "{username}")
    public ResponseEntity<RestApiResponse> deleteUser(@PathVariable String username) {
        User user;
        if (Validator.isEmailFormat(username))
            user = userService.getUserByEmail(username);
        else {
            user = userService.getUserByUsername(username);
        }
        if (user == null)
            throw new MyNotFoundException(String.format("User %s not found.", username));
        user.setStatus(AppStatus.INACTIVE);
        userService.saveUser(user);
        return responseUtil.successResponse("Ok");
    }
}

package com.swithExample.driven.services;

import com.swithExample.driven.models.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserByEmail(String email);

}

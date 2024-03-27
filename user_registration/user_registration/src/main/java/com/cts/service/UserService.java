package com.cts.service;

import com.cts.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    boolean validateUserService(String username,String password);

    boolean validateUser(String username, String password);



    boolean getByUsername(String username);
}

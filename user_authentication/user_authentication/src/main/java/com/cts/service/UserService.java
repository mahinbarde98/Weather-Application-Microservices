package com.cts.service;


import com.cts.model.UserCredentials;

public interface UserService {
     UserCredentials saveDetails(UserCredentials user);

     boolean validateUser(String username, String Password);

     public void init();

}

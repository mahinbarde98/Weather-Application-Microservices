package com.cts.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserCredentials {

    String username;

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    String password;

    public UserCredentials() {
    }



}
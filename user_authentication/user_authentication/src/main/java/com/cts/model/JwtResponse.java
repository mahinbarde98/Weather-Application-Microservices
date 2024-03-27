package com.cts.model;

import java.util.Arrays;

public class JwtResponse {
String token;
String username;


    public JwtResponse(String token,String username){
        this.token = token;
        this.username = username;

    }

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

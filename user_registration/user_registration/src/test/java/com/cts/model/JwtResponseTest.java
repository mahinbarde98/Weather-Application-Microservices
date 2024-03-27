package com.cts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtResponseTest {
    @Test
    public void testGettersAndSetters()
    {
        JwtResponse jwtResponse =new JwtResponse();
        //testing Setters
        String[] roles = {"USER"};
        jwtResponse.setToken("token");
        jwtResponse.setRole(roles);
        //testing Getters

        assertEquals(roles,jwtResponse.getRole());
       assertEquals("token",jwtResponse.getToken());
    }
}

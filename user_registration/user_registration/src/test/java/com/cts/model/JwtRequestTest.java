package com.cts.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class JwtRequestTest {
    @Test
    public void testGettersAndSetters()
    {
        JwtRequest jwtRequest =new JwtRequest();
        //testing Setters
        jwtRequest.setUsername("test");
        jwtRequest.setPassword("test@pass");
        //testing Getters
        assertEquals("test",jwtRequest.getUsername());
        assertEquals("test@pass",jwtRequest.getPassword());
    }
}

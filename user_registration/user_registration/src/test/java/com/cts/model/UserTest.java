package com.cts.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        // Test setters
        user.setUsername("maheshbarde@123");
        user.setPassword("maheshpass");
        user.setName("Mahesh Barde");
        // Test getters
        assertEquals("maheshbarde@123", user.getUsername());
        assertEquals("maheshpass", user.getPassword());
        assertEquals("Mahesh Barde", user.getName());
    }

    @Test
    public void testToString() {
        User user = new User("jane_smith", "strongPassword", "Jane Smith");
        String expectedToString = "User{userId='jane_smith', userPassword='strongPassword', name='Jane Smith'}";
        assertEquals(expectedToString, user.toString());
    }

    @Test
    public void testParameterizedConstructor() {
        User user = new User("test", "test123", "test tester");
        assertEquals("test", user.getUsername());
        assertEquals("test123", user.getPassword());
        assertEquals("test tester", user.getName());
    }

    @Test
    public void testDefaultConstructor() {
        User user = new User();
        // Ensure default values are set to null
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getName());
    }
}
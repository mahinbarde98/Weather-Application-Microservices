package com.cts.controller;


import com.cts.model.User;
import com.cts.exception.UserAlreadyExistException;
import com.cts.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User("newUser", "password", "role");

        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<?> responseEntity = userController.registerUser(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());

        verify(userService, times(1)).saveUser(user);
//        verify(userService, times(1)).sendUserCredentials("newUser", "password");
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        User existingUser = new User("existingUser", "password", "role");

        when(userService.saveUser(existingUser)).thenThrow(new UserAlreadyExistException("User Already Exists"));

        ResponseEntity<?> responseEntity = userController.registerUser(existingUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Check the user data", responseEntity.getBody());

        verify(userService, times(1)).saveUser(existingUser);
//        verify(userService, times(0)).sendUserCredentials(anyString(), anyString());
    }

    @Test
    void testAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "pass1", "role1"));
        userList.add(new User("user2", "pass2", "role2"));

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<?> responseEntity = userController.allUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());

        verify(userService, times(1)).getAllUsers();
    }

}

package com.cts.service;


    import com.cts.model.User;
import com.cts.exception.UserAlreadyExistException;
import com.cts.repository.UserRepository;
//import com.cts.user_registration.feign.JwtFeignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

        @Mock
        private UserRepository userRepository;

//        @Mock
//        private JwtFeignService jwtFeignService;

        @InjectMocks
        private UserServiceImpl userService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void testSaveUser_Success() {
            User user = new User("test", "test", "role");

            when(userRepository.findById("test")).thenReturn(Optional.empty());
            when(userRepository.save(user)).thenReturn(user);

            User savedUser = userService.saveUser(user);

            assertNotNull(savedUser);
            assertEquals("test", savedUser.getUsername());
            assertEquals("test", savedUser.getPassword());
           // assertEquals("role", savedUser.getRole());

            verify(userRepository, times(1)).findById("test");
            verify(userRepository, times(1)).save(user);
        }

        @Test
        void testSaveUser_UserAlreadyExists() {
            User existingUser = new User("existingUser", "password", "role");

            when(userRepository.findById("existingUser")).thenReturn(Optional.of(existingUser));

            assertThrows(UserAlreadyExistException.class, () -> userService.saveUser(existingUser));

            verify(userRepository, times(1)).findById("existingUser");
            verify(userRepository, times(0)).save(existingUser);
        }

        @Test
        void testGetAllUsers() {
            List<User> userList = new ArrayList<>();
            userList.add(new User("user1", "pass1", "role1"));
            userList.add(new User("user2", "pass2", "role2"));

            when(userRepository.findAll()).thenReturn(userList);

            List<User> result = userService.getAllUsers();

            assertEquals(2, result.size());
            assertEquals("user1", result.get(0).getUsername());
            assertEquals("user2", result.get(1).getUsername());

            verify(userRepository, times(1)).findAll();
        }



}

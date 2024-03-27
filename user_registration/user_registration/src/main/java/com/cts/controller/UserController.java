package com.cts.controller;


import com.cts.exception.UserAlreadyExistException;
import com.cts.service.UserServiceImpl;
import com.cts.model.User;
import com.cts.model.UserCredentials;
//import com.cts.service.KafkaProducerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class UserController {

    public UserServiceImpl userService;
//    public final KafkaProducerService kafkaProducerService;

//    @Autowired
//    public UserController(UserServiceImpl userService, KafkaProducerService kafkaProducerService){
//        super();
//        this.userService = userService;
//        this.kafkaProducerService = kafkaProducerService;
//    }

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initUsers() {
        userService.init();
    }

//
//    @PostMapping("/save")
//    @CircuitBreaker(name = "resgistertoauthenticateCircuitBreaker" , fallbackMethod = "fallbackMethod")
//    public ResponseEntity<?> registerUser(@RequestBody User user){
//
//        try{
//
//            if(user!=null) {
//                String username = user.getUsername();
//                String password = user.getPassword();
//                System.out.println("userCredentials: " + username + " " + password);
//
//                UserCredentials userCredentials =new UserCredentials();
//                userCredentials.setUsername(username);
//                userCredentials.setPassword(password);
//                User newUser = userService.saveUser(user);
//                try {
//                    //kafkaProducerService.sendMessage(userCredentials);
//                    userService.sendUserCredentials(username,password);
//                    System.out.println("Successfully Published the user = '" + userCredentials.toString() + "' to the Credentials1");
//                    // userService.sendUserCredentials(username, password);
//                }catch (Error ex){
//                    System.out.println("ERROR"+ex.getMessage());
//                }
//                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//            }
//            else {
//                throw new UserAlreadyExistException("User Data is already registered with the system");
//            }
//
//        }catch (UserAlreadyExistException e){
//            return new ResponseEntity<String>("Check the user data",HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//    }


    @PostMapping("/save")
    @CircuitBreaker(name = "resgistertoauthenticateCircuitBreaker", fallbackMethod = "fallbackMethod")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user != null) {
            if (userService.getByUsername(user.getUsername())){
                try {


                    System.out.println("userCredentials: " + user.getUsername() + " " + user.getPassword());


                    UserCredentials userCredentials = new UserCredentials();
                    userCredentials.setUsername(user.getUsername());
                    userCredentials.setPassword(user.getPassword());
                    userService.saveUser(user);
                    //kafkaProducerService.sendMessage(userCredentials);
                    userService.sendUserCredentials(user.getUsername(), user.getPassword());
                    System.out.println("Successfully Published the user = '" + userCredentials.toString() + "' to the Credentials1");
                    // userService.sendUserCredentials(username, password);



                    return new ResponseEntity<>(user, HttpStatus.CREATED);


                }
                    catch (UserAlreadyExistException e) {
                    return new ResponseEntity<String>("Check the user data", HttpStatus.INTERNAL_SERVER_ERROR);

                    }
            //   return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            throw new UserAlreadyExistException("User Data is already registered with the system");
        }
    } else

    {
        return new ResponseEntity<>("User Object is null", HttpStatus.NOT_FOUND);
    }
    }

    public ResponseEntity<?> fallbackMethod(User user, Exception ex) {
        user.setUsername("dummyuser");
        user.setPassword("dummypassword");
        log.info("Circuit Breaker Executed", ex.getMessage());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {

        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }


//    public boolean validateUser(@RequestBody String username , String password){
//
//       if(userService.validateUser(username,password))
//       {
//           return true;
//       }
//        return false;
//    }
//    @GetMapping("/validate")
//    public User getUserByUername(@RequestBody String username){
//        User user = userService.getByUsername(username);
//        System.out.println("User : " +user);
//        return  user;
//    }
//    @PostMapping("/login")
//    public JwtResponse logIn(@RequestBody JwtRequest jwtRequest) throws ServletException {
//        String username =jwtRequest.getUsername();
//        String password = jwtRequest.getPassword();
//        String jwtToken;
//        //List<Role> role;
//        String [] role ={"admin"};
//        if(username ==null || password ==null)
//        {
//            throw new ServletException("Please enter valid credentials");
//        }
//
//
//
//        // boolean flag = userService.validateUser(username,password);
//        boolean flag =userService.validateUser(username,password);
//        System.out.println(flag);
//
//
//        if(!flag)
//        {
//            throw new ServletException("Invalid credentials");
//        }
//
//        return userService.generateToken(jwtRequest);
//    }

}

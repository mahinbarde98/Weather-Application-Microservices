package com.cts.service;

import com.cts.model.JwtRequest;
import com.cts.model.JwtResponse;
import com.cts.model.User;
import com.cts.exception.UserAlreadyExistException;
//import com.cts.user_registration.feign.JwtFeignService;
import com.cts.model.UserCredentials;
import com.cts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    public UserRepository userRepository;

    @Autowired
    com.cts.user_registration.feign.JwtFeignService jwtFeignService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;

    }
    @Override
    public User saveUser(User user) {
        if (user!=null){
            Optional<User>userById = userRepository.findById(user.getUsername());

            if(userById.isPresent()){
                throw new UserAlreadyExistException("User Already Registered with the database");
            }
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
       // List<User> userList = userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public boolean validateUserService(String username, String password) {
        User user = userRepository.ValideUser(username,password);
      //  User user  = userRepository.findByUserIdAndUserPassword(userId,userPassword);
        System.out.println("User :"+ username );
        if (user!=null){
            return  true;
        }
        return false;
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.ValideUser(username,password);
        System.out.println("user :" + user );
        if (user!=null){return true;}
        return false;
    }



    @Override
    public boolean getByUsername(String username) {
        Optional<User> user =userRepository.findById(username);
        if (user.isPresent())
            return true;
        return false;
    }

    public void init() {
        User user1 = new User("admin","admin","admin");
        User user2 = new User("user","user","user");
        userRepository.save(user1);
        userRepository.save(user2);

    }


    public JwtResponse generateToken(JwtRequest jwtRequest)
    {
       return jwtFeignService.logIn(jwtRequest);
    }



    public void sendUserCredentials(String username ,String password){

        UserCredentials userCredentials = new UserCredentials();

        userCredentials.setUsername(username);
        userCredentials.setPassword(password);

        jwtFeignService.registerUserdetails(userCredentials);

    }
}

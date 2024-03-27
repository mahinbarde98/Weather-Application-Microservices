package com.cts.service;

import com.cts.model.JwtRequest;
import com.cts.model.JwtResponse;
import com.cts.model.UserCredentials;
import com.cts.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    public static final String SECRET = "secret";
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    @Lazy
    public JwtService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    public Jws<Claims> validateToken(final String token) {
      //Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        Jws<Claims> claimsJws =Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return claimsJws;
    }
    public JwtResponse generateToken(JwtRequest jwtRequest) throws ServletException {

        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        String jwtToken;
        //List<Role> role;

        if (username == null || password == null) {
            throw new ServletException("Please enter valid credentials");
        }


        // boolean flag = userService.validateUser(username,password);
        boolean flag = userService.validateUser(username, password);
        System.out.println(flag);


        if (!flag) {
            throw new ServletException("Invalid credentials");
        } else {
            Optional<UserCredentials> user = userRepository.findById(username);


            jwtToken = Jwts.builder()
                    .setSubject(username)
                    .claim("username",username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3000000))
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();

            //}
            return new JwtResponse(jwtToken,username);
        }


    }
}

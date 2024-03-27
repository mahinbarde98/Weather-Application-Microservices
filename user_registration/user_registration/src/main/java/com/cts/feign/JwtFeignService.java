package com.cts.user_registration.feign;

import com.cts.model.JwtRequest;
import com.cts.model.JwtResponse;

import com.cts.model.UserCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "USER-AUTHENTICATION")
public interface JwtFeignService {
    @PostMapping("api/v2/register")
    public JwtResponse logIn(@RequestBody JwtRequest jwtRequest);

    @PostMapping("api/v2/register")
    public void registerUserdetails (@RequestBody UserCredentials userCredentials);

}

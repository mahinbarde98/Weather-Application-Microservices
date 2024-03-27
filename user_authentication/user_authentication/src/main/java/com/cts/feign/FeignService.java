package com.cts.feign;

import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-AUTHENTICATION")
public interface FeignService {

    @GetMapping("api/v1/validate")
    public boolean validateUser(@RequestParam String username, @RequestParam String password);

}

package com.cts.favorite.controller;
import com.cts.favorite.exception.DuplicateCityException;
import com.cts.favorite.model.FavoriteCity;
import com.cts.favorite.response.ResponseHandler;
import com.cts.favorite.service.FavoriteCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v4")
public class FavoriteCityController {

    @Autowired
    private FavoriteCityService service;

    @GetMapping("/{userId}")
    public List<FavoriteCity> getFavoriteCities(@PathVariable String userId) {
        return service.getFavoriteCities(userId);
    }

    @PostMapping("/{userId}/{cityName}")
    public ResponseEntity<?> addFavoriteCity(@PathVariable String userId, @PathVariable String cityName) {
        try {
            service.addFavoriteCity(userId, cityName);
            return ResponseHandler.generateResponse("City added to favorites successfully", HttpStatus.CREATED);
        } catch (DuplicateCityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}/{cityName}")
    public void removeFavoriteCity(@PathVariable String userId, @PathVariable String cityName) {
        service.removeFavoriteCity(userId, cityName);
    }
}

package com.cts.weatherApp.controller;

import com.cts.weatherApp.entity.Weather;
import com.cts.weatherApp.response.ResponseHandler;
import com.cts.weatherApp.service.WeatherServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "WeatherController", description = "Getting weather accessing third party api")
public class WeatherController {
    private final WeatherServiceImpl weatherService;


    public WeatherController(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

//    @GetMapping("/weather/{city}")
//    public Weather getWeather(@PathVariable String city) {
//
//        return  weatherService.getWeather(city);
//    }
    @GetMapping("/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {

        try {
            Weather weather = weatherService.getWeather(city);
            return ResponseHandler.generateResponse("Succeesfull accessed third party API", HttpStatus.OK,weather);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Somethinng Wrong", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }

    }



}

package com.cts.weatherApp.service;

import com.cts.weatherApp.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherServiceImpl implements WeatherService{
    private static final String API_KEY = "5bca776f735e450785d80355231611";
    private static final String API_URL = "http://api.weatherapi.com/v1/current.json?key=";

    private final RestTemplate restTemplate;


    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getWeather(String city) {
        String url = API_URL + API_KEY + "&q="+city +"&aqi=no";
       Weather weather =restTemplate.getForObject(url,Weather.class);
       return weather;
    }
}

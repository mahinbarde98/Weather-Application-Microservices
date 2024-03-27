package com.cts.weatherApp.service;

import com.cts.weatherApp.entity.Weather;

public interface WeatherService {
    Weather getWeather(String city);
}

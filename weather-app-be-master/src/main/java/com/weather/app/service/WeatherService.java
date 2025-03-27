package com.weather.app.service;

import com.weather.app.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    WeatherResponse getWeather(String city);
}

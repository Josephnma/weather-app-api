package com.weather.app.controller;

import com.weather.app.model.WeatherResponse;
import com.weather.app.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public WeatherResponse getWeather(@RequestParam(defaultValue = "Lagos") String city) {
        return weatherService.getWeather(city);
    }
}

package com.weather.app.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherResponse {
    // Getters and Setters
    private String city;
    private double temperature;
    private int humidity;
    private int pressure;
    private String description;

    // Constructor
    public WeatherResponse() {}

}

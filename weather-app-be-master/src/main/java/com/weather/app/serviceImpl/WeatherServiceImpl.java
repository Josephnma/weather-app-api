package com.weather.app.serviceImpl;

import com.weather.app.model.WeatherResponse;
import com.weather.app.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;


    @Override
    public WeatherResponse getWeather(String city) {
        try {
            String url = String.format(apiUrl, city, apiKey);
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForObject(url, Map.class);

            if (response != null) {
                WeatherResponse weather = new WeatherResponse();
                weather.setCity(city);
                weather.setTemperature((Double) ((Map<String, Object>) response.get("main")).get("temp"));
                weather.setHumidity((Integer) ((Map<String, Object>) response.get("main")).get("humidity"));
                weather.setPressure((Integer) ((Map<String, Object>) response.get("main")).get("pressure"));
                weather.setDescription(((Map<String, Object>) ((List<?>) response.get("weather")).get(0)).get("description").toString());
                return weather;
            }
            return null;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("City not found or API error");
        }
    }
}

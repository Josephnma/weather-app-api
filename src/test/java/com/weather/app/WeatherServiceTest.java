package com.weather.app;

import com.weather.app.model.WeatherResponse;
import com.weather.app.serviceImpl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService.apiKey = "fake-api-key";  // Mock API key
        weatherService.apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
    }

    @Test
    void testGetWeather_Success() {
        // Mock API response
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Object> mainData = new HashMap<>();
        mainData.put("temp", 30.0);
        mainData.put("humidity", 65);
        mainData.put("pressure", 1012);
        mockResponse.put("main", mainData);
        mockResponse.put("weather", List.of(Map.of("description", "clear sky")));

        // Mock API call
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Execute method
        WeatherResponse response = weatherService.getWeather("Lagos");

        // Debugging
        System.out.println("City: " + response.getCity());
        System.out.println("Temperature: " + response.getTemperature());
        System.out.println("Description: " + response.getDescription());

        // Assertions
        assertNotNull(response);
        assertEquals("Lagos", response.getCity());
        assertEquals(30.0, response.getTemperature());
        assertEquals("clear sky", response.getDescription());
    }

    @Test
    void testGetWeather_ApiError() {
        // Mock API failure
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            weatherService.getWeather("UnknownCity");
        });

        assertEquals("City not found or API error", exception.getMessage());
    }
}

package com.sis.rosario.flight_services.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sis.rosario.flight_services.dto.WeatherRequest;
import com.sis.rosario.flight_services.dto.WeatherResponse;
import com.sis.rosario.flight_services.service.WeatherService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather API", description = "Consulta clima")
public class WeatherController {

    private final WeatherService weatherService;
    
    public WeatherController(WeatherService weatherService) {
		super();
		this.weatherService = weatherService;
	}

	@GetMapping
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam String city,
            @RequestParam String country_code,
            @RequestParam(required = false) String units) {
        
        WeatherRequest request = new WeatherRequest(city, country_code, units);
        return ResponseEntity.ok(weatherService.getWeather(request));
    }
}
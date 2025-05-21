package com.sis.rosario.flight_services.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sis.rosario.flight_services.dto.OpenWeatherResponse;
import com.sis.rosario.flight_services.dto.WeatherRequest;
import com.sis.rosario.flight_services.dto.WeatherResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherService {
	
    @Qualifier("weatherWebClient") 
	private final WebClient weatherWebClient;
	private final String apiKey;

	public WeatherService(WebClient weatherWebClient, @Value("${weather.api.key}") String apiKey) {
		
			this.weatherWebClient = weatherWebClient;
			this.apiKey = apiKey;
	    }
	
    @Cacheable(value = "weatherData", key = "{#request.city, #request.countryCode}")
    public WeatherResponse getWeather(WeatherRequest request) {
        OpenWeatherResponse apiResponse = weatherWebClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/weather")
                .queryParam("q", request.city() + "," + request.countryCode())
                .queryParam("units", request.units() != null ? request.units() : "metric")
                .queryParam("appid", apiKey)
                .build())
            .retrieve()
            .bodyToMono(OpenWeatherResponse.class)
            .timeout(Duration.ofSeconds(10))
            .block();
        
        return new WeatherResponse(
        		apiResponse.name(),
        		request.countryCode(),
        		apiResponse.main().temp(),
        		apiResponse.main().humidity(),
        		request.units() != null ? request.units() : "metric",
        		apiResponse.weather().get(0).description()
        		);
    }
}
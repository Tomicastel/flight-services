package com.sis.rosario.flight_services.dto;

import java.util.List;

public record OpenWeatherResponse(
	    String name,
	    List<WeatherInfo> weather,
	    MainInfo main,
	    long dt
	) {
	    public record WeatherInfo(String main, String description) {}
	    public record MainInfo(double temp, int humidity) {}
	}
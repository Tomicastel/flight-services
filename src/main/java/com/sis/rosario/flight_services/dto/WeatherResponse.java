package com.sis.rosario.flight_services.dto;

public record WeatherResponse(
	    String city,
	    String country,
	    double temperature,
	    int humidity,
	    String unit,
	    String description
	) {}
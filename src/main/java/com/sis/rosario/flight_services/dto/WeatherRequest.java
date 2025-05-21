package com.sis.rosario.flight_services.dto;

import jakarta.validation.constraints.NotBlank;

public record WeatherRequest(
	    @NotBlank String city,
	    @NotBlank String countryCode,
	    String units 
	) {}

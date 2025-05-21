package com.sis.rosario.flight_services.dto;

public record FlightState(String icao, String callSign, String country, 
		double lon, double lat, double speed) {}
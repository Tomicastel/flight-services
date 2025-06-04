package com.sis.rosario.flight_services.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sis.rosario.flight_services.dto.FlightState;
import com.sis.rosario.flight_services.dto.OpenSkyStateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenSkyService {

    @Qualifier("openSkyWebClient") 
    private final WebClient openSkyWebClient;

    public OpenSkyService(WebClient openSkyWebClient) {
		super();
		this.openSkyWebClient = openSkyWebClient;
	}

    public List<FlightState> getFlightStates() {
        return openSkyWebClient.get()
            .uri("/states/all")
            .retrieve()
            .bodyToMono(OpenSkyStateResponse.class)
            .timeout(Duration.ofSeconds(30))
            .block()
            .states()
            .stream()
            .map(this::mapToFlightState)
            .toList();
    }

    private FlightState mapToFlightState(List<Object> state) {
        return new FlightState(
                (String) state.get(0),  // icao24
                (String) state.get(1),  // callsign
                (String) state.get(2),  // originCountry
                safeGetDouble(state, 5), // longitude 
                safeGetDouble(state, 6), // latitude
                safeGetDouble(state, 9)  // velocity
            );
    }

    // Método helper para manejar valores nulos
    private double safeGetDouble(List<Object> state, int index) {
        if (index >= state.size()) return 0.0; // Índice fuera de rango
        Object value = state.get(index);
        return (value instanceof Number) ? ((Number) value).doubleValue() : 0.0;
    }
}
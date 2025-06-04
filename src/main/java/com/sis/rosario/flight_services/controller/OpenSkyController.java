package com.sis.rosario.flight_services.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sis.rosario.flight_services.dto.FlightState;
import com.sis.rosario.flight_services.service.OpenSkyService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/opensky")
@Tag(name = "Open Sky API", description = "Vuelos en tiempo real")
public class OpenSkyController {

    private final OpenSkyService openSkyService;
    
    public OpenSkyController(OpenSkyService openSkyService) {
		super();
		this.openSkyService = openSkyService;
	}

	@GetMapping("/states/all")
    public ResponseEntity<List<FlightState>> getStates() {
        return ResponseEntity.ok(
            openSkyService.getFlightStates()
        );
    }

}
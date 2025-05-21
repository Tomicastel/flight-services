package com.sis.rosario.flight_services.controller;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sis.rosario.flight_services.model.Flight;
import com.sis.rosario.flight_services.repository.FlightRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flight API", description = "Operaciones para gestión de vuelos")
public class FlightController {

		private final FlightRepository flightRepository;
		
	    // Inyección por constructor (recomendado)
	    public FlightController(FlightRepository flightRepository) {
	        this.flightRepository = flightRepository;
	    }
	    
	    @Operation(summary = "Crea vuelos")
	    @CacheEvict(value = "flights", allEntries = true) // Limpia cache al crear nuevo vuelo
	    @PostMapping("/addFlight")
	    public ResponseEntity<Flight> createFlight(@Valid @RequestBody Flight flight) {
	        System.out.println("Datos recibidos: " + flight.toString()); // Verifica en consola

	        Flight savedFlight = flightRepository.save(flight);
	        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(savedFlight);
	    }
	    
	    @Operation(summary = "Muestra todos los vuelos")
	    @GetMapping("/getAll")
	    public List<Flight> getAllFlights() {
	        return flightRepository.findAll();
	    }
	    
	    @GetMapping("/test")
	    @PreAuthorize("hasRole('USER')")
	    public String test() {
	        return "Funciona!";
	    }
	    
	    @Operation(summary = "Busca vuelos por origen", description = "Filtra por origen")
	    @Cacheable("flights") // Cachea resultados
	    @GetMapping("/by-origin/{origin}")
	    public List<Flight> getFlightsByOrigin(@PathVariable String origin) {
	        return flightRepository.findByOrigin(origin);
	    }
	    
	    @Operation(summary = "Busca vuelos por numero")
	    @GetMapping("/by-flightNumber/{flightNumber}")
	    public Flight getFlightByNumber(@PathVariable String flightNumber) {
	    	return flightRepository.findByFlightNumber(flightNumber);
	    }
	    
	    @Operation(summary = "Busca vuelos por origen y destino", description = "Filtra por origen y destino")
	    @GetMapping
	    public List<Flight> searchFlights(
	        @Parameter(description = "Ciudad de origen") @RequestParam String origin,
	        @Parameter(description = "Ciudad de destino") @RequestParam String destination
	    ) { 
	    
	    	return flightRepository.findByOriginAndDestination(origin, destination);
	    }
}

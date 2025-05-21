package com.sis.rosario.flight_services.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sis.rosario.flight_services.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	
    List<Flight> findByOrigin(String origin);
    
    Flight findByFlightNumber(String flightNumber);
    
    List<Flight> findByDestination(String destination);
    
    default List<Flight> findByOriginAndDestination(String origin, String destination) {
    	
        return findAll().stream()
                .filter(flight -> flight.getOrigin().equalsIgnoreCase(origin))
                .filter(flight -> flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }
    
}
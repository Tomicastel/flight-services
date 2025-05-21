package com.sis.rosario.flight_services.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fligth")  
@Schema (description = "Modelo de datos para vuelo")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long id;
    
    @Schema(description = "Número de vuelo", example = "AV-2024")
    @Column(name = "flight_number") // Si la columna en DB tiene otro nombre
    @JsonProperty("flightNumber")  // Si el JSON usa camelCase
    private String flightNumber;
    
    @Schema(description = "Origen del vuelo", example = "Lima")
    private String origin;
    
    @Schema(description = "Destino del vuelo", example = "Buenos Aires")
    private String destination;

    public Flight() {
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}   
	
    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

}

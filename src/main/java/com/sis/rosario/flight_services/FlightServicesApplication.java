package com.sis.rosario.flight_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // Activa el sistema de cache
public class FlightServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightServicesApplication.class, args);
	}

}

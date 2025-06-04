package com.sis.rosario.flight_services.dto;

import java.util.List;

public record RegisterRequest(String username, String password, List<String> roles) {

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}	
}


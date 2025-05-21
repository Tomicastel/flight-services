package com.sis.rosario.flight_services.dto;

import java.util.List;

public record OpenSkyStateResponse(List<List<Object>> states) {}
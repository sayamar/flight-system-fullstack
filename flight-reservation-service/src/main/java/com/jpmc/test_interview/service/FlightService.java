package com.jpmc.test_interview.service;

import com.jpmc.test_interview.dto.FlightDto;

import java.util.List;

public interface FlightService {

    List<FlightDto> findFlights(String departureCity, String arrivalCity, String departureTime);

}

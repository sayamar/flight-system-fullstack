package com.jpmc.test_interview.service;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.dto.ReservationDto;

public interface ReservationService {

    ReservationDto bookFlight(FlightReservationRequest flightReservationRequest);
}

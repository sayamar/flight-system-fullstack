package com.jpmc.test_interview.api.mapper;

import com.jpmc.test_interview.FlightReservationResponse;
import com.jpmc.test_interview.dto.ReservationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlightResponseMapper {

    public FlightReservationResponse mapToResponse(ReservationDto reservationDto) {
        FlightReservationResponse flightReservationResponse = new FlightReservationResponse();
        flightReservationResponse.setBookingConfirmationNumber(String.valueOf(reservationDto.getBookingConfirmationNumber()));
        flightReservationResponse.setStatus(reservationDto.getStatus());
        flightReservationResponse.setFileName(reservationDto.getFileName());

        return flightReservationResponse;

    }
}

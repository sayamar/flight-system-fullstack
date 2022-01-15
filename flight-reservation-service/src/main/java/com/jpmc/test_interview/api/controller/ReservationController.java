package com.jpmc.test_interview.api.controller;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.FlightReservationResponse;
import com.jpmc.test_interview.api.mapper.FlightResponseMapper;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.exception.ReservationBookingFailedException;
import com.jpmc.test_interview.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ReservationController {

    private final ReservationService reservationService;
    private final FlightResponseMapper flightResponseMapper;

    @ExceptionHandler(ReservationBookingFailedException.class)
    @PostMapping(value = "/bookFlight", produces = "application/json")
    public ResponseEntity<FlightReservationResponse> bookFlight(
            @RequestBody @NotNull FlightReservationRequest flightReservationRequest) {
        log.info("Request for booking the flight {} ", flightReservationRequest.getFlightNo());
        ReservationDto reservationDto = this.reservationService.bookFlight(flightReservationRequest);
        return new ResponseEntity<>(flightResponseMapper.mapToResponse(reservationDto), HttpStatus.OK);
    }

}

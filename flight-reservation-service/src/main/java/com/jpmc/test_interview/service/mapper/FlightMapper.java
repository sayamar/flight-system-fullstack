package com.jpmc.test_interview.service.mapper;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Passenger;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.dto.PaymentDto;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.util.FlightConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FlightMapper {

    public List<FlightDto> ToFlightDtoList(List<Flight> flightList) {
        List<FlightDto> flightDtoList = new ArrayList<>();
        for ( Flight eachFlight : flightList ){
            flightDtoList.add(this.mapToFlightDto(eachFlight));
        }
        return flightDtoList;
    }

    public PaymentDto toPaymentDto(FlightReservationRequest reservationRequest){
        return PaymentDto.builder()
                .cardHolderName(reservationRequest.getCardHolderName())
                .cardNumber(reservationRequest.getCardNumber())
                .expiryDate(reservationRequest.getExpiryDate())
                .build();
    }

    public Reservation toReservation(FlightReservationRequest reservationRequest, Flight flight) {
        return Reservation.builder()
                .flightNo(flight.getFlightNo())
                .passenger(toPassenger(reservationRequest))
                .status(FlightConstants.BOOKING_NEW)
                .bookingDateTime(LocalDateTime.now())
                .build();
    }

    public ReservationDto toReservationDto(Reservation reservation, String filePath){
        return ReservationDto.builder()
                .flightNo(reservation.getFlightNo())
                .status(reservation.getStatus())
                .bookingConfirmationNumber(reservation.getBookingConfirmationNumber())
                .fileName(filePath)
                .build();
    }

    private Passenger toPassenger(FlightReservationRequest reservationRequest){
        return Passenger.builder()
                .email(reservationRequest.getEmail())
                .firstName(reservationRequest.getFirstName())
                .lastName(reservationRequest.getLastName())
                .phone(reservationRequest.getPhone())
                .build();
    }

    private FlightDto mapToFlightDto(Flight eachFlight){
        return FlightDto.builder()
                .flightNo(eachFlight.getFlightNo())
                .departureCity(eachFlight.getDepartureCity())
                .arrivalCity(eachFlight.getArrivalCity())
                .departureTime(eachFlight.getDepartureTime().toString())
                .arrivalTime(eachFlight.getArrivalTime().toString())
                .id(eachFlight.getId())
                .airLines(eachFlight.getAirLine())
                .build();
    }
}

package com.jpmc.test_interview;

import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Passenger;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.dto.PaymentDto;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.util.FlightConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public final class FlightTestDataProvider {

    private FlightTestDataProvider(){

    }

    public static List<Flight> getFlightList(){
        List<Flight> fightsList = new ArrayList<>();

        Flight flight = new Flight();
        flight.setFlightNo("SQ100");
        flight.setArrivalCity("London");
        flight.setDepartureCity("Singapore");
        flight.setArrivalTime(new Timestamp(System.currentTimeMillis()));
        flight.setDepartureTime(new Timestamp(System.currentTimeMillis()));
        flight.setId(10L);
        flight.setAirLine("SG Airlines");
        fightsList.add(flight);
      return fightsList;
    }

    public static List<Flight> returnsEmptyFlightList() {
        return new ArrayList<>();
    }

    public static List<FlightDto> returnsEmptyFlightDtoList() {
        return new ArrayList<>();
    }

    public static List<FlightDto> getFlightDtoList() {
        List<FlightDto> flightDtoList = new ArrayList<>();

        flightDtoList.add(FlightDto
                .builder()
                .id(2L)
                        .flightNo("SQ100")
                        .arrivalCity("London")
                        .departureCity("Singapore")
                        .arrivalTime(new Timestamp(System.currentTimeMillis()).toString())
                        .departureTime(new Timestamp(System.currentTimeMillis()).toString())
                        .airLines("SG Airlines")
                        .build()
            );
        return flightDtoList;
    }

    public static PaymentDto getPaymentDtoForSuccess(){
        return PaymentDto.builder()
                .cardHolderName("MARUTHI")
                .cardNumber("5234123209871111")
                .expiryDate("20-08-2024")
                .build();

    }

    public static PaymentDto getPaymentDtoForFailure(){
        return PaymentDto.builder()
                .build();
    }

    public static String getTestDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static FlightReservationRequest getRequest() {

        FlightReservationRequest flightReservationRequest = new FlightReservationRequest();
        flightReservationRequest.setFlightId("10");
        flightReservationRequest.setFlightNo("SQ100");
        flightReservationRequest.setFirstName("Shiva");
        flightReservationRequest.setLastName("Sankar");
        flightReservationRequest.setPhone("90284789");
        flightReservationRequest.setEmail("ms@gmail.com");
        flightReservationRequest.setCardHolderName("MS");
        flightReservationRequest.setCardNumber("51230000432199999");
        flightReservationRequest.setExpiryDate("20-12-2024");
        return flightReservationRequest;

    }

    public static Reservation getReservationDetails(){
        return Reservation.builder()
                .flightNo("SQ100")
                .bookingConfirmationNumber(200L)
                .bookingDateTime(LocalDateTime.now())
                .status(FlightConstants.BOOKING_NEW)
                .passenger(getPassengerDetails())
                .build();
    }

    public static Optional<Reservation> getReservationDetailsOptional(){
        return Optional.of(Reservation.builder()
                .flightNo("SQ100")
                .bookingConfirmationNumber(200L)
                .bookingDateTime(LocalDateTime.now())
                .status(FlightConstants.BOOKING_NEW)
                .passenger(getPassengerDetails())
                .build());
    }

    public static ReservationDto getReservationDto() {
            return ReservationDto.builder()
                    .bookingConfirmationNumber(2L)
                    .status(FlightConstants.BOOKING_CONFIRMED)
                    .fileName("C:/Users/Data")
                    .build();
    }

    public static Flight getFlight() {
        Flight flight = new Flight();
        flight.setFlightNo("SQ100");
        flight.setArrivalCity("London");
        flight.setDepartureCity("Singapore");
        flight.setArrivalTime(new Timestamp(System.currentTimeMillis()));
        flight.setDepartureTime(new Timestamp(System.currentTimeMillis()));
        flight.setId(10L);
        flight.setAirLine("SG Airlines");
        return flight;
    }

    public static ResponseEntity<List<FlightDto>> getResponseEntityWithFlightDtoList() {
        List<FlightDto> flightDtoList = new ArrayList<>();

        flightDtoList.add(FlightDto
                .builder()
                .id(2L)
                .flightNo("SQ100")
                .arrivalCity("London")
                .departureCity("Singapore")
                .arrivalTime(new Timestamp(System.currentTimeMillis()).toString())
                .departureTime(new Timestamp(System.currentTimeMillis()).toString())
                .airLines("SG Airlines")
                .build()
        );
        return new ResponseEntity<>(flightDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<List<FlightDto>> getResponseEntityWithNoFlights() {
        List<FlightDto> flightDtoList = new ArrayList<>();
        return new ResponseEntity<>(flightDtoList, HttpStatus.OK);
    }

    public static ResponseEntity<FlightReservationResponse> getFlightReservationResponse() {

        FlightReservationResponse flightReservationResponse = new FlightReservationResponse();
        flightReservationResponse.setBookingConfirmationNumber("10");
        flightReservationResponse.setStatus(FlightConstants.BOOKING_CONFIRMED);
        flightReservationResponse.setFileName("C:/Users/Maruthi/data");

        return new ResponseEntity<>(flightReservationResponse,HttpStatus.OK);

    }

    private static Passenger getPassengerDetails() {
        return Passenger.builder()
                .firstName("Shiva")
                .lastName("Sankar")
                .email("ms@gmail.com")
                .phone("90284789")
                .passengerId(2L)
                .build();
    }

}

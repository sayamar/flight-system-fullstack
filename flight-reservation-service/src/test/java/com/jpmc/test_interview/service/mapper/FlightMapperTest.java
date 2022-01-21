package com.jpmc.test_interview.service.mapper;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.dto.FlightDto;
import com.jpmc.test_interview.dto.PaymentDto;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.util.FlightConstants;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FlightMapperTest {

    @InjectMocks
    private FlightMapper flightMapper;

    @Test
    public void test_ShouldMapFlightListToFlightDtoList(){
        // Given
        List<Flight> flightList = FlightTestDataProvider.getFlightList();
        // When
        List<FlightDto> flightDtoList = flightMapper.ToFlightDtoList(flightList);
        // Then
        assertNotNull(flightDtoList);
        assertEquals(flightList.get(0).getFlightNo(),flightDtoList.get(0).getFlightNo());
        assertEquals(flightList.get(0).getArrivalCity(),flightDtoList.get(0).getArrivalCity());
        assertEquals(flightList.get(0).getDepartureCity(),flightDtoList.get(0).getDepartureCity());
        assertEquals(flightList.get(0).getId(),flightDtoList.get(0).getId());
        assertEquals(flightList.get(0).getAirLine(),flightDtoList.get(0).getAirLines());
        assertEquals(flightList.get(0).getDepartureTime().toString(),flightDtoList.get(0).getDepartureTime());
        assertEquals(flightList.get(0).getArrivalTime().toString(),flightDtoList.get(0).getArrivalTime());
    }

    @Test
    public void test_ShouldMapFlightRequestToPaymentDto() {
        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        // When
        PaymentDto paymentDto = flightMapper.toPaymentDto(flightReservationRequest);
        // Then
        assertNotNull(paymentDto);
        assertEquals(flightReservationRequest.getCardHolderName(),paymentDto.getCardHolderName());
        assertEquals(flightReservationRequest.getCardNumber(),paymentDto.getCardNumber());
        assertEquals(flightReservationRequest.getExpiryDate(),paymentDto.getExpiryDate());

    }
    @Test
    public void test_ShouldMapFlightRequestToReservation() {
        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        Flight flight = FlightTestDataProvider.getFlight();
        // When
        Reservation reservation = flightMapper.toReservation(flightReservationRequest,flight);
        // Then
        assertNotNull(flightReservationRequest.getFlightNo(),reservation.getFlightNo());
        assertNotNull(FlightConstants.BOOKING_NEW,reservation.getStatus());
        assertEquals(flightReservationRequest.getFirstName(),reservation.getPassenger().getFirstName());
        assertEquals(flightReservationRequest.getLastName(),reservation.getPassenger().getLastName());
        assertEquals(flightReservationRequest.getMiddleName(),reservation.getPassenger().getMiddleName());
        assertEquals(flightReservationRequest.getDateOfBirth(),reservation.getPassenger().getDateOfBirth());
        assertEquals(flightReservationRequest.getPhone(),reservation.getPassenger().getPhone());
        assertEquals(flightReservationRequest.getEmail(),reservation.getPassenger().getEmail());

    }
    @Test
    public void test_ShouldMapToReservationDto() {
        // Given
        Reservation reservation = FlightTestDataProvider.getReservationDetails();
        String filePath ="C:/Users/Maruthi/Data";
        // When
        ReservationDto reservationDto = flightMapper.toReservationDto(reservation,filePath);
        // Then
        assertNotNull(reservationDto);
        assertEquals(reservation.getBookingConfirmationNumber(),reservationDto.getBookingConfirmationNumber());
        assertEquals(reservation.getStatus(),reservationDto.getStatus());
        assertEquals(filePath,reservationDto.getFileName());
    }

    @SneakyThrows
    private Date convertStringToDate(String date) {
        return StringUtils.isEmpty(date) ? null : new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

}

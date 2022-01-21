package com.jpmc.test_interview.service;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.data.FlightRepository;
import com.jpmc.test_interview.data.ReservationRepository;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.exception.FlightNotFoundException;
import com.jpmc.test_interview.service.mapper.FlightMapper;
import com.jpmc.test_interview.util.PDFGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private PDFGenerator pdfGenerator;

    @Mock
    private PaymentService paymentService;

    @Test
    public void test_ShouldBookTheFlightAndReturnsConfirmation() {

        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        when(flightRepository.findById(any())).thenReturn(FlightTestDataProvider.getFlightList().stream().findFirst());
        when(reservationRepository.saveAndFlush(any())).thenReturn(FlightTestDataProvider.getReservationDetails());
        when(paymentService.toPay(any())).thenReturn("Success");
        when(reservationRepository.findById(any())).thenReturn(FlightTestDataProvider.getReservationDetailsOptional());
        when(flightMapper.toReservationDto(any(),any())).thenReturn(FlightTestDataProvider.getReservationDto());
        // When
        ReservationDto reservationDto = reservationServiceImpl.bookFlight(flightReservationRequest);

        // Then
        assertNotNull(reservationDto);
        assertEquals(reservationDto.getBookingConfirmationNumber(), 2L);

        // Verify
        verify(flightRepository,times(1)).findById(any());
        verify(reservationRepository,times(2)).saveAndFlush(any());
        verify(paymentService,times(1)).toPay(any());
        verify(reservationRepository,times(1)).findById(any());
        verify(flightMapper,times(1)).toReservationDto(any(),any());

    }

    @Test
    public void test_ShouldNotBookTheFlightIfPaymentFailures() {

        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        when(flightRepository.findById(any())).thenReturn(FlightTestDataProvider.getFlightList().stream().findFirst());
        when(reservationRepository.saveAndFlush(any())).thenReturn(FlightTestDataProvider.getReservationDetails());
        when(paymentService.toPay(any())).thenReturn("Failed");
        // When
        ReservationDto reservationDto = reservationServiceImpl.bookFlight(flightReservationRequest);

        // Then
        assertNotNull(reservationDto);
        assertEquals("In Progress", reservationDto.getStatus());
        assertEquals("Payment processing failed",reservationDto.getMessage());

        // Verification
        verify(flightRepository,times(1)).findById(any());
        verify(reservationRepository, times(1)).saveAndFlush(any());
        verify(paymentService, times(1)).toPay(any());

    }

    @Test
    public void test_ShouldReturnsExceptionIfFlightNotFound() {

        // Given
        FlightReservationRequest flightReservationRequest = FlightTestDataProvider.getRequest();
        when(flightRepository.findById(any())).thenThrow(new FlightNotFoundException("Flight Not Found Exception"));
        //when
        RuntimeException exception = assertThrows(FlightNotFoundException.class, () -> {
            reservationServiceImpl.bookFlight(flightReservationRequest);
        });

    }
}

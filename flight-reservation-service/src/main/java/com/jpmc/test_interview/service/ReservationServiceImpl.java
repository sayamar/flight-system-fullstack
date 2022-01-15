package com.jpmc.test_interview.service;

import com.jpmc.test_interview.FlightReservationRequest;
import com.jpmc.test_interview.data.FlightRepository;
import com.jpmc.test_interview.data.ReservationRepository;
import com.jpmc.test_interview.data.entity.Flight;
import com.jpmc.test_interview.data.entity.Reservation;
import com.jpmc.test_interview.dto.ReservationDto;
import com.jpmc.test_interview.exception.FlightNotFoundException;
import com.jpmc.test_interview.service.mapper.FlightMapper;
import com.jpmc.test_interview.util.FlightConstants;
import com.jpmc.test_interview.util.PDFGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationServiceImpl implements  ReservationService{

    private final ReservationRepository reservationRepository;
    private final PaymentService paymentService;
    private final FlightMapper flightMapper;
    private final PDFGenerator pdfGenerator;
    private final FlightRepository flightRepository;


    @Override
    public ReservationDto bookFlight(FlightReservationRequest flightReservationRequest)  {
        log.info("Booking started for {} ", flightReservationRequest.getFlightNo());

        // Finding th flights
        Flight flightDetails = this.flightRepository.findById(Long.valueOf(flightReservationRequest.getFlightId()))
                .orElseThrow( () -> new FlightNotFoundException("Flight not found exception ") );

        Reservation reservation = this.reservationRepository.
                saveAndFlush(this.flightMapper.toReservation(flightReservationRequest, flightDetails));

        // Update the booking status to Confirmed after Payment confirmation
        if (paymentService.toPay(this.flightMapper.toPaymentDto(flightReservationRequest)).equals("Success")) {

            Reservation reservationUpdateRequest = this.reservationRepository.findById(reservation.getBookingConfirmationNumber())
                    .orElseThrow(() -> new FlightNotFoundException("Reservation Id Not Found exception "));
            reservationUpdateRequest.setStatus(FlightConstants.BOOKING_CONFIRMED);

            this.reservationRepository.saveAndFlush(reservationUpdateRequest);

            String generatedItineraryFileName = this.pdfGenerator.generatePdf(flightReservationRequest, reservationUpdateRequest,flightDetails);
           return this.flightMapper.toReservationDto(reservation,generatedItineraryFileName);
        }

        return ReservationDto
                .builder()
                .message("Payment processing failed")
                .status(FlightConstants.BOOKING_NEW)
                .build();
    }
}

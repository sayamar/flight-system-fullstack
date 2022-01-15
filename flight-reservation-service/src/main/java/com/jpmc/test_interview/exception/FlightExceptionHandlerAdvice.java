package com.jpmc.test_interview.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class FlightExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = { FlightNotFoundException.class })
    protected ResponseEntity<Object> handleIfFlightNotFound(
            FlightNotFoundException ex, WebRequest request) {
        String error = "Flight Not Found: " + ex.getMessage();
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
    }

    @ExceptionHandler(value = { ReservationBookingFailedException.class })
    protected ResponseEntity<Object> handleIfPDFReportFailed(
            ReservationBookingFailedException ex, WebRequest request) {
        String error = "Reservation Failed: " + ex.getMessage();
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @ExceptionHandler(value = { DownloadTicketException.class })
    protected ResponseEntity<Object> handleIfCustomerIsUnableToDownloadPDF(
            DownloadTicketException ex, WebRequest request) {
        String error = "Download Failed: " + ex.getMessage();
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }
}

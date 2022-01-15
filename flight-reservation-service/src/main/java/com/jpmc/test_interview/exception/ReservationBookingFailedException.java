package com.jpmc.test_interview.exception;

public class ReservationBookingFailedException extends  RuntimeException {
    public ReservationBookingFailedException(String errMsg) {
        super(errMsg);
    }
}

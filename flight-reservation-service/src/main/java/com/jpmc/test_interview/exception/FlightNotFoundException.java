package com.jpmc.test_interview.exception;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(String errMsg) {
        super(errMsg);
    }
}

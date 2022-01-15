package com.jpmc.test_interview.exception;

public class DownloadTicketException extends RuntimeException {

    public DownloadTicketException(String errMsg) {
        super(errMsg);
    }
}

package com.jpmc.test_interview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ReservationDto {

    private Long bookingConfirmationNumber;
    private String status;
    private Long passengerId;
    private String flightNo;
    private LocalDateTime bookingDateTime;
    private String message;
    private String fileName;
}

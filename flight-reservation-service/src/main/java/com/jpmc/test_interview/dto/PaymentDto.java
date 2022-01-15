package com.jpmc.test_interview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class PaymentDto {

    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
}

package com.jpmc.test_interview.service;

import com.jpmc.test_interview.FlightTestDataProvider;
import com.jpmc.test_interview.dto.PaymentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @Test
    public void testShouldConfirmSuccessfulPayment() {

        // Given
        PaymentDto paymentDto = FlightTestDataProvider.getPaymentDtoForSuccess();
        // When
        String paymentConfirmation = paymentServiceImpl.toPay(paymentDto);
        // Then
        assertNotNull(paymentConfirmation);
        assertEquals("Success",paymentConfirmation);

    }

    @Test
    public void testShouldConfirmFailurePayment() {

        // Given
        PaymentDto paymentDto = FlightTestDataProvider.getPaymentDtoForFailure();
        // When
        String paymentConfirmation = paymentServiceImpl.toPay(paymentDto);
        // Then
        assertNotNull(paymentConfirmation);
        assertEquals("Failed",paymentConfirmation);

    }

}

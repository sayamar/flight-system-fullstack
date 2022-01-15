package com.jpmc.test_interview.service;

import com.jpmc.test_interview.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String toPay(PaymentDto paymentDto) {
        log.info("Payment is in progress with payment gateway provider");
        if (Objects.nonNull(paymentDto) && Objects.nonNull(paymentDto.getCardHolderName())
                && Objects.nonNull(paymentDto.getCardNumber()) && Objects.nonNull(paymentDto.getExpiryDate())) {
            /** Connecting to Payment Gateway Adapter to process the payment **/
            return "Success";
        }

        return "Failed";
    }
}

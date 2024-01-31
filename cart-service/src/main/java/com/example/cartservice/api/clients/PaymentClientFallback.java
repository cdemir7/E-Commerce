package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.CreateCartPaymentRequest;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "paymentClient")
public class PaymentClientFallback implements PaymentClient{
    @Override
    public void processCartPayment(CreateCartPaymentRequest request) {
        log.info("PAYMENT SERVICE IS DOWN!");
        throw new BusinessException("PAYMENT SERVICE IS DOWN!");
    }
}

package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.CreateCartPaymentRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment", fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @PutMapping(value = "/api/v1/payment/process")
    @Retry(name = "flightSearch")
    void processCartPayment(@RequestBody CreateCartPaymentRequest request);
}

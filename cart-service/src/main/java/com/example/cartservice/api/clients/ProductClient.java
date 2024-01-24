package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "nvntry", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping(value = "/api/v1/products/check-quantity-exists/{productId}")
    @Retry(name = "flightSearch")
    ClientResponse checkIfQuantityExists(@PathVariable UUID productId);
}

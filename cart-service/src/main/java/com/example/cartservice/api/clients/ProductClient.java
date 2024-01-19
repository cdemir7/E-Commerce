package com.example.cartservice.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "nvntry")
public interface ProductClient {
    @GetMapping(value = "/api/v1/products/check-quantity-exists/{productId}")
    void checkIfQuantityExists(@PathVariable UUID productId);
}

package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "nvntry", fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping(value = "/api/v1/products/check-quantity-exists/{productId}")
    ClientResponse checkIfQuantityExists(@PathVariable UUID productId);
}

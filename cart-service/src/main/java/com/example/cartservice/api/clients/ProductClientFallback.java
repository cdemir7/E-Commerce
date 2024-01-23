package com.example.cartservice.api.clients;

import com.example.commonpackage.utils.dto.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient{
    @Override
    public ClientResponse checkIfQuantityExists(UUID productId) {
        log.info("PRODUCT SERVICE IS DOWN!");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }
}

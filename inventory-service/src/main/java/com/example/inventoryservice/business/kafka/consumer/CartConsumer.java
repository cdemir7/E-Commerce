package com.example.inventoryservice.business.kafka.consumer;

import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.events.cart.CartDeletedEvent;
import com.example.inventoryservice.business.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartConsumer {
    private final ProductService service;

    @KafkaListener(
            topics = "cart-created",
            groupId = "nvntry-cart-create"
    )
    public void consume(CartCreatedEvent event){
        service.changeQuantityDecreaseByProduct(event.getProductId());
        log.info("Cart Created Event Consumed");
    }

    @KafkaListener(
            topics = "cart-deleted",
            groupId = "nvntry-cart-delete"
    )
    public void consume(CartDeletedEvent event){
        service.changeQuantityIncreaseByProduct(event.getProductId());
        log.info("Cart Deleted Event Consumed");
    }
}

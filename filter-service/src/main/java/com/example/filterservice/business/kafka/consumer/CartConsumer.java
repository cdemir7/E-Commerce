package com.example.filterservice.business.kafka.consumer;

import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "cart-created",
            groupId = "flt-cart-create"
    )
    public void consume(CartCreatedEvent event){
        var filter = service.getByProductId(event.getProductId());
        filter.setQuantity(filter.getQuantity() - 1);
        service.add(filter);
        log.info("Cart Created Event Consumed");
    }
}

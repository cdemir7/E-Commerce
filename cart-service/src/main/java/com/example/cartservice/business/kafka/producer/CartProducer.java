package com.example.cartservice.business.kafka.producer;

import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.events.inventory.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(CartCreatedEvent event){
        log.info("Cart Created Event");
        Message<CartCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "cart-created")
                .build();

        kafkaTemplate.send(message);
    }
}

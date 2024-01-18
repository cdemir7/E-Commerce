package com.example.inventoryservice.kafka.producer;

import com.example.commonpackage.events.inventory.CategoryDeletedEvent;
import com.example.commonpackage.events.inventory.ProductCreatedEvent;
import com.example.commonpackage.events.inventory.ProductDeletedEvent;
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
public class InventoryProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(ProductCreatedEvent event){
        log.info("Car Created Event");
        Message<ProductCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "product-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ProductDeletedEvent event){
        log.info("Car Deleted Event");
        Message<ProductDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "product-deleted")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CategoryDeletedEvent event){
        log.info("Category Deleted Event");
        Message<CategoryDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "category-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}

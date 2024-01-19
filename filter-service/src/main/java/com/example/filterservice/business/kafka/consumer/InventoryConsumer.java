package com.example.filterservice.business.kafka.consumer;

import com.example.commonpackage.events.inventory.CategoryDeletedEvent;
import com.example.commonpackage.events.inventory.ProductCreatedEvent;
import com.example.commonpackage.events.inventory.ProductDeletedEvent;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "product-created",
            groupId = "product-create"
    )
    public void consume(ProductCreatedEvent event){
        var filter = mapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("Car Created Event Consumed");
    }

    @KafkaListener(
            topics = "product-deleted",
            groupId = "product-delete"
    )
    public void consume(ProductDeletedEvent event){
        service.deleteByProductId(event.getProductId());
        log.info("Car Deleted Event Consumed");
    }

    @KafkaListener(
            topics = "category-deleted",
            groupId = "category-delete"
    )
    public void consume(CategoryDeletedEvent event){
        service.deleteAllByCategory(event.getCategoryId());
        log.info("Category Deleted Event Consumed");
    }
}

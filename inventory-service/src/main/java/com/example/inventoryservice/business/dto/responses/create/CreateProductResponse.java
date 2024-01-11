package com.example.inventoryservice.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {
    private UUID id;
    private UUID categoryId;
    private String name;
    private double unitPrice;
    private int quantity;
    private String description;
}


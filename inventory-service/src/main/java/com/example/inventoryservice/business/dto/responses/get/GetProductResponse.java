package com.example.inventoryservice.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponse {
    private UUID categoryId;
    private UUID id;
    private String name;
    private double unitPrice;
    private int quantity;
    private String description;
}

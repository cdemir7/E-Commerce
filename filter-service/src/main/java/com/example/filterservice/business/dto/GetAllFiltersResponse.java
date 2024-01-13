package com.example.filterservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFiltersResponse {
    private UUID id;
    private UUID productId;
    private UUID categoryId;
    private String categoryName;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String description;
}

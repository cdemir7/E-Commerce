package com.example.filterservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    @Id
    private UUID id;
    private UUID productId;
    private UUID categoryId;
    private String categoryName;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String description;
}

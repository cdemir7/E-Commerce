package com.example.inventoryservice.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotBlank
    private UUID categoryId;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Min(1)
    private double unitPrice;

    @NotNull
    @Min(0)
    private int quantity;

    private String description;
}


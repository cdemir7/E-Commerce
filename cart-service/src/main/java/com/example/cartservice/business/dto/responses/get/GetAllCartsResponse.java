package com.example.cartservice.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartsResponse {
    private UUID productId;
    private String name;
    private double unitPrice;
    private int quantity;
}

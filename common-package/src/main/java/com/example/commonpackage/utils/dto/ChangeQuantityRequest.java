package com.example.commonpackage.utils.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ChangeQuantityRequest {
    private UUID id;
    private int quantity;
}

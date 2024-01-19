package com.example.inventoryservice.business.rules;

import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.inventoryservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfProductExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("PRODUCT_NOT_EXISTS");
        }
    }
    public void checkIfQuantityExists(UUID productId){
        var product = repository.findById(productId).orElseThrow();
        if (product.getQuantity() <= 0){
            throw new BusinessException("OUT_OF_STOCK");
        }
    }
}

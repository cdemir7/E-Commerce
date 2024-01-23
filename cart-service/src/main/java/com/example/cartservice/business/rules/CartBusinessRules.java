package com.example.cartservice.business.rules;

import com.example.cartservice.api.clients.ProductClient;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository repository;
    private final ProductClient client;

    public void checkIfCartExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("CART_NOT_EXISTS");
        }
    }

    public void ensureProductIsQuantity(UUID productId){
        var response = client.checkIfQuantityExists(productId);
        if (!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }
}

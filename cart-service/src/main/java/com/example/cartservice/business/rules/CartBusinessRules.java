package com.example.cartservice.business.rules;

import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartBusinessRules {
    private final CartRepository repository;

    public void checkIfCartExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("CART_NOT_EXISTS");
        }
    }
}

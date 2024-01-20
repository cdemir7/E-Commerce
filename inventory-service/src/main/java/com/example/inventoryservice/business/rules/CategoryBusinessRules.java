package com.example.inventoryservice.business.rules;

import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.inventoryservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository repository;

    public void checkIfCategoryExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("CATEGORY_NOT_EXISTS");
        }
    }
}

package com.example.inventoryservice.business.concretes;

import com.example.inventoryservice.business.abstracts.CategoryService;
import com.example.inventoryservice.business.dto.requests.create.CreateCategoryRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCategoryRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCategoryResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCategoriesResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCategoryResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    @Override
    public List<GetAllCategoriesResponse> getAll() {
        return null;
    }

    @Override
    public GetCategoryResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        return null;
    }

    @Override
    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}

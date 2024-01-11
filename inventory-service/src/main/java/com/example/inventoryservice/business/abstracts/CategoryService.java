package com.example.inventoryservice.business.abstracts;

import com.example.inventoryservice.business.dto.requests.create.CreateCategoryRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCategoryRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCategoryResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCategoriesResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCategoryResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(UUID id);
    CreateCategoryResponse add(CreateCategoryRequest request);
    UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request);
    void delete(UUID id);
}

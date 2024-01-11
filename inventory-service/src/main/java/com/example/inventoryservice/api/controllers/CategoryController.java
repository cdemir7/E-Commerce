package com.example.inventoryservice.api.controllers;

import com.example.inventoryservice.business.abstracts.CategoryService;
import com.example.inventoryservice.business.dto.requests.create.CreateCategoryRequest;
import com.example.inventoryservice.business.dto.requests.create.CreateProductRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateCategoryRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateProductRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateCategoryResponse;
import com.example.inventoryservice.business.dto.responses.create.CreateProductResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCategoriesResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetCategoryResponse;
import com.example.inventoryservice.business.dto.responses.get.GetProductResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateCategoryResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public List<GetAllCategoriesResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@Valid @RequestBody CreateCategoryRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(UUID id){
        service.delete(id);
    }
}

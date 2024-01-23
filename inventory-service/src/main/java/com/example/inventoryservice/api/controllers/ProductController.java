package com.example.inventoryservice.api.controllers;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.inventoryservice.business.abstracts.ProductService;
import com.example.inventoryservice.business.dto.requests.create.CreateProductRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateProductRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateProductResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllCategoriesResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetProductResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@Valid @RequestBody CreateProductRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateProductRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/check-quantity-exists/{id}")
    public ClientResponse checkIfQuantityExists(@PathVariable UUID id){
        return service.checkIfQuantityExists(id);
    }
}

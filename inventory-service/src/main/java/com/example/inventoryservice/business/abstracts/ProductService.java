package com.example.inventoryservice.business.abstracts;

import com.example.commonpackage.utils.dto.ClientResponse;
import com.example.inventoryservice.business.dto.requests.create.CreateProductRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateProductRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateProductResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetProductResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(UUID id);
    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(UUID id, UpdateProductRequest request);
    void delete(UUID id);
    ClientResponse checkIfQuantityExists(UUID productId);
    void changeQuantityDecreaseByProduct(UUID id);
    void changeQuantityIncreaseByProduct(UUID id);
}

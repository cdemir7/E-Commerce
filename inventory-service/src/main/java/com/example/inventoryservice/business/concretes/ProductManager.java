package com.example.inventoryservice.business.concretes;

import com.example.inventoryservice.business.abstracts.ProductService;
import com.example.inventoryservice.business.dto.requests.create.CreateProductRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateProductRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateProductResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetProductResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    @Override
    public List<GetAllProductsResponse> getAll() {
        return null;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        return null;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}

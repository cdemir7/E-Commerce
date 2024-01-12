package com.example.inventoryservice.business.concretes;

import com.example.commonpackage.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.ProductService;
import com.example.inventoryservice.business.dto.requests.create.CreateProductRequest;
import com.example.inventoryservice.business.dto.requests.update.UpdateProductRequest;
import com.example.inventoryservice.business.dto.responses.create.CreateProductResponse;
import com.example.inventoryservice.business.dto.responses.get.GetAllProductsResponse;
import com.example.inventoryservice.business.dto.responses.get.GetProductResponse;
import com.example.inventoryservice.business.dto.responses.update.UpdateProductResponse;
import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllProductsResponse> getAll() {
        var products = repository.findAll();
        var response = products.
                stream()
                .map(product -> mapper.forResponse().map(product, GetAllProductsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        var product = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(product, GetProductResponse.class);

        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        var product = mapper.forRequest().map(request, Product.class);
        product.setId(UUID.randomUUID());
        var createdProduct = repository.save(product);
        var response = mapper.forResponse().map(createdProduct, CreateProductResponse.class);

        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        var product = mapper.forRequest().map(request, Product.class);
        product.setId(id);
        var updatedProduct = repository.save(product);
        var response = mapper.forResponse().map(updatedProduct, UpdateProductResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

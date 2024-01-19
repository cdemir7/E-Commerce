package com.example.cartservice.business.abstracts;

import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.get.GetCartResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<GetAllCartsResponse> getAll();
    GetCartResponse getById(UUID id);
    CreateCartResponse add(CreateCartRequest request);
    UpdateCartResponse update(UUID id, UpdateCartRequest request);
    void delete(UUID id);
}

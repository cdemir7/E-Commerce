package com.example.cartservice.business.concretes;

import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.get.GetCartResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartManager implements CartService {
    private final CartRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllCartsResponse> getAll() {
        var carts = repository.findAll();
        var response = carts
                .stream()
                .map(cart -> mapper.forResponse().map(cart, GetAllCartsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCartResponse getById(UUID id) {
        var cart = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(cart, GetCartResponse.class);

        return response;
    }

    @Override
    public CreateCartResponse add(CreateCartRequest request) {
        var cart = mapper.forRequest().map(request, Cart.class);
        cart.setId(UUID.randomUUID());
        var createdCart = repository.save(cart);
        var response = mapper.forResponse().map(createdCart, CreateCartResponse.class);

        return response;
    }

    @Override
    public UpdateCartResponse update(UUID id, UpdateCartRequest request) {
        var cart = mapper.forRequest().map(request, Cart.class);
        cart.setId(id);
        var updatedCart = repository.save(cart);
        var response = mapper.forResponse().map(updatedCart, UpdateCartResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

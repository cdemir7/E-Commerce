package com.example.cartservice.api.controllers;

import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.get.GetCartResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService service;

    @GetMapping
    public List<GetAllCartsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCartResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCartResponse add(@Valid @RequestBody CreateCartRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCartResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCartRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}

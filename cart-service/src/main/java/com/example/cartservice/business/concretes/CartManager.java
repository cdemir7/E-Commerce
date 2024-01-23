package com.example.cartservice.business.concretes;

import com.example.cartservice.api.clients.ProductClient;
import com.example.cartservice.business.abstracts.CartService;
import com.example.cartservice.business.dto.requests.create.CreateCartRequest;
import com.example.cartservice.business.dto.requests.update.UpdateCartRequest;
import com.example.cartservice.business.dto.responses.create.CreateCartResponse;
import com.example.cartservice.business.dto.responses.get.GetAllCartsResponse;
import com.example.cartservice.business.dto.responses.get.GetCartResponse;
import com.example.cartservice.business.dto.responses.update.UpdateCartResponse;
import com.example.cartservice.business.rules.CartBusinessRules;
import com.example.cartservice.entities.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.commonpackage.events.cart.CartCreatedEvent;
import com.example.commonpackage.events.cart.CartDeletedEvent;
import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
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
    private final KafkaProducer producer;
    private final CartBusinessRules rules;

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
        rules.checkIfCartExists(id);
        var cart = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(cart, GetCartResponse.class);

        return response;
    }

    @Override
    public CreateCartResponse add(CreateCartRequest request) {
        rules.ensureProductIsQuantity(request.getProductId());
        var cart = mapper.forRequest().map(request, Cart.class);
        cart.setId(UUID.randomUUID());
        cart.setQuantity(getTotalQuantity(cart));
        var createdCart = repository.save(cart);
        sendKafkaCartCreatedEvent(request.getProductId());
        var response = mapper.forResponse().map(createdCart, CreateCartResponse.class);

        return response;
    }

    @Override
    public UpdateCartResponse update(UUID id, UpdateCartRequest request) {
        rules.checkIfCartExists(id);
        var cart = mapper.forRequest().map(request, Cart.class);
        cart.setId(id);
        var updatedCart = repository.save(cart);
        var response = mapper.forResponse().map(updatedCart, UpdateCartResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfCartExists(id);
        sendKafkaCartDeletedEvent(id);
        repository.deleteById(id);

    }


    private int getTotalQuantity(Cart cart){
        return cart.getQuantity() - 1;
    }
    private void sendKafkaCartCreatedEvent(UUID productId) {
        producer.sendMessage(new CartCreatedEvent(productId), "cart-created");
    }
    private void sendKafkaCartDeletedEvent(UUID id) {
        var productId = repository.findById(id).orElseThrow().getProductId();
        producer.sendMessage(new CartDeletedEvent(productId), "cart-deleted");
    }
}

package com.example.paymentservice.api.controllers;

import com.example.paymentservice.business.abstracts.PaymentService;
import com.example.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }

}

package com.example.paymentservice.business.concretes;

import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.paymentservice.business.abstracts.PaymentService;
import com.example.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.UpdatePaymentResponse;
import com.example.paymentservice.business.rules.PaymentBusinessRules;
import com.example.paymentservice.entities.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PaymentBusinessRules rules;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        var payments = repository.findAll();
        var response = payments
                .stream()
                .map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        var payment = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request.getCardNumber());
        var payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(UUID.randomUUID());
        var createdPayment = repository.save(payment);
        var response = mapper.forResponse().map(createdPayment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        var payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        var updatedPayment = repository.save(payment);
        var response = mapper.forResponse().map(updatedPayment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByCardNumber(request.getCardNumber());
        rules.checkIfBalanceEnough(payment.getBalance(), request.getUnitPrice());
        payment.setBalance(payment.getBalance() - request.getUnitPrice());
        repository.save(payment);
    }
}

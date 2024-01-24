package com.example.paymentservice.business.abstracts;

import com.example.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.example.paymentservice.business.dto.requests.CreatePaymentRequest;
import com.example.paymentservice.business.dto.requests.UpdatePaymentRequest;
import com.example.paymentservice.business.dto.responses.CreatePaymentResponse;
import com.example.paymentservice.business.dto.responses.GetAllPaymentsResponse;
import com.example.paymentservice.business.dto.responses.GetPaymentResponse;
import com.example.paymentservice.business.dto.responses.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
    void delete(UUID id);

    void processRentalPayment(CreateRentalPaymentRequest request);
}

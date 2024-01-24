package com.example.paymentservice.business.dto.requests;
import com.example.commonpackage.utils.dto.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {
    @Min(1)
    private double balance;
}


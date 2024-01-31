package com.example.paymentservice.business.rules;

import com.example.commonpackage.utils.dto.CreateCartPaymentRequest;
import com.example.commonpackage.utils.exceptions.BusinessException;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id){
        if (!repository.existsById(id)){
            throw new BusinessException("Payment Not Found");
        }
    }

    public void checkIfBalanceEnough(double balance, double unitPrice){
        if (balance < unitPrice){
            throw new BusinessException("Not Enough Money");
        }
    }

    public void checkIfCardExists(String cardNumber){
        if (repository.existsByCardNumber(cardNumber)){
            throw new BusinessException("Card Number Already Exists");
        }
    }

    public void checkIfPaymentIsValid(CreateCartPaymentRequest request){
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        )){
            throw new BusinessException("Ödeme Geçerli Değil");
        }
    }
}

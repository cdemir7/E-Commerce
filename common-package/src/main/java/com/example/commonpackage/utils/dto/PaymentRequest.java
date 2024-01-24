package com.example.commonpackage.utils.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @Length(min = 16, max = 16, message = "Kart numarası 16 haneli olmak zorundadır.")
    private String cardNumber;

    @Length(min = 4, message = "Kart sahibi bilgisi en az 4 haneli  olmak zorundadır. ")
    private String cardHolder;

    @Min(value = 2023, message = "Kar kullanım yılı en az 2023 olmalıdır.")
    private int cardExpirationYear;

    @Min(1)
    @Max(12)
    private int cardExpirationMonth;

    @Length(min = 3, max = 3)
    private String cardCvv;
}

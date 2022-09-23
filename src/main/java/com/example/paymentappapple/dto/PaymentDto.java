package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentDto {

    @NotNull(message = " amount cannot be null")
    private double amount;

    @NotNull(message = "invoice id cannot be null")
    private Long invoiceId;

}

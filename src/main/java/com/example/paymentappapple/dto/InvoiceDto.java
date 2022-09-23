package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class InvoiceDto {

    @NotNull(message = "invoice amount cannot be null")
    private double amount;

    @NotNull(message = "invoice issued date cannot be null")
    private Date issued;

    @NotNull(message = "invoice due date cannot be null")
    private Date due;

    @NotNull(message = "order id  cannot be null")
    private Long orderId;
}

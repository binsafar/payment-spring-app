package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDto {

    @NotNull(message = "order date cannot be null")
    private Date date;
    @NotNull(message = "customer id  cannot be null")
    private Long customerId;
}

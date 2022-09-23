package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class DetailDto {

    @NotNull(message = "order id cannot be null")
    private Long orderId;

    @NotNull(message = "product id cannot be null")
    private Long productId;

    @NotNull(message = "quantity cannot be null")
    private Integer quantity;

}

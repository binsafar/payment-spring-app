package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {

    @NotNull(message = "Customer name cannot be empty")
    private String name;

    @NotNull(message = "Customer country cannot be empty")
    private String country;

    @NotNull(message = "Customer address cannot be empty")
    private String address;

    @NotNull(message = "Customer phone cannot be empty")
    private String phone;
}

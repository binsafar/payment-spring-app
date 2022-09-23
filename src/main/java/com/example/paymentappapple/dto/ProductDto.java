package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotNull(message = " name cannot be null")
    private String name;

    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "price cannot be null")
    private double price;

    @NotNull(message = "attachment  id cannot be null")
    private Integer attachmentId;

    @NotNull(message = "category id cannot be null")
    private Long categoryId;
}

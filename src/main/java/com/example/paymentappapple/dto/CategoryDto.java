package com.example.paymentappapple.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull(message = "category name cannot be empty")
    private String name;
}

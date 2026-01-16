package com.example.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @NotNull
    private String customerId;

    @NotEmpty
    private List<String> productIds;

    @NotNull
    private Double totalAmount;
}

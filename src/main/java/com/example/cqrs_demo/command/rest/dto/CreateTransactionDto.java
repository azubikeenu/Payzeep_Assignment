package com.example.cqrs_demo.command.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class CreateTransactionDto {
    @NotNull(message = "transaction amount  is a required field")
    @Positive(message = "transaction amount must be a non-negative number")
    private BigDecimal amount ;
}

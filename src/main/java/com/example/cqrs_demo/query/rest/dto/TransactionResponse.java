package com.example.cqrs_demo.query.rest.dto;

import com.example.cqrs_demo.core.entities.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class TransactionResponse {

    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private Timestamp date;
    private String maskedPan;
    private Status status;
    private String transactionRef;
}

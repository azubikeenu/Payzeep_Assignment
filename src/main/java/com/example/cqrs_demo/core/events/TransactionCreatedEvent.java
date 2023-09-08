package com.example.cqrs_demo.core.events;

import com.example.cqrs_demo.core.entities.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionCreatedEvent {
    private String id;

    private BigDecimal amount;

    private Timestamp date;

    private String maskedPan;

    private Status status;

    private String transactionRef;
}

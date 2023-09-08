package com.example.cqrs_demo.command.commands;

import com.example.cqrs_demo.core.entities.Status;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class CreateTransactionCommand {

    @TargetAggregateIdentifier
    private String id;

    private BigDecimal amount;

    private Timestamp date;

    private String maskedPan;

    private Status status;

    private String transactionRef;
}

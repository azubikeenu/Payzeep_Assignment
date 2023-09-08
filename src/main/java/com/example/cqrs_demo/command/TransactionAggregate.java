package com.example.cqrs_demo.command;

import com.example.cqrs_demo.command.commands.CreateTransactionCommand;
import com.example.cqrs_demo.core.entities.Status;
import com.example.cqrs_demo.core.events.TransactionCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Aggregate
@NoArgsConstructor
public class TransactionAggregate {

    @AggregateIdentifier
    private String id;

    private BigDecimal amount;

    private Timestamp date;

    private String maskedPan;

    private Status status;

    private String transactionRef;


    @CommandHandler
    public TransactionAggregate(CreateTransactionCommand createTransactionCommand) {
        if (createTransactionCommand.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
        TransactionCreatedEvent transactionCreatedEvent = new TransactionCreatedEvent();
        BeanUtils.copyProperties(createTransactionCommand, transactionCreatedEvent);
        AggregateLifecycle.apply(transactionCreatedEvent);
    }


    @EventSourcingHandler
    public void on(TransactionCreatedEvent transactionCreatedEvent) {
        this.id = transactionCreatedEvent.getId();
        this.amount = transactionCreatedEvent.getAmount();
        this.date = transactionCreatedEvent.getDate();
        this.status = transactionCreatedEvent.getStatus();
        this.transactionRef = transactionCreatedEvent.getTransactionRef();
        this.maskedPan = transactionCreatedEvent.getMaskedPan();
    }
}




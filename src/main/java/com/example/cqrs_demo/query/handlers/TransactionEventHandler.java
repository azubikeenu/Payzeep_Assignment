package com.example.cqrs_demo.query.handlers;

import com.example.cqrs_demo.core.entities.Transaction;
import com.example.cqrs_demo.core.events.TransactionCreatedEvent;
import com.example.cqrs_demo.core.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("transaction-group")
public class TransactionEventHandler {
    private final TransactionRepository transactionRepository;

    @EventHandler
    public void on(TransactionCreatedEvent transactionCreatedEvent) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionCreatedEvent, transaction);

        try {
            // persists the command object in the main storage
            final Transaction savedTransaction = transactionRepository.save(transaction);
            log.info("Saving transaction with ref: {} with id {}", savedTransaction.getTransactionRef(), savedTransaction.getId());

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

    }


    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException ex) {
        throw ex;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception ex) throws Exception {
        throw ex;
    }
}

package com.example.cqrs_demo.command.handlers;

import com.example.cqrs_demo.core.entities.TransactionLookup;
import com.example.cqrs_demo.core.events.TransactionCreatedEvent;
import com.example.cqrs_demo.core.repository.TransactionLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("transaction-group")
public class TransactionLookupEventsHandler {
    final TransactionLookupRepository transactionLookupRepository;
    @EventHandler
    public void on(TransactionCreatedEvent transactionCreatedEvent) {
        TransactionLookup productLookupEntity = new TransactionLookup(transactionCreatedEvent.getId());
        transactionLookupRepository.save(productLookupEntity);
    }

}

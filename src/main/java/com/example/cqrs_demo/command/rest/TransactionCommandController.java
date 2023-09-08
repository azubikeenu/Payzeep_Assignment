package com.example.cqrs_demo.command.rest;

import com.example.cqrs_demo.command.commands.CreateTransactionCommand;
import com.example.cqrs_demo.command.rest.dto.CreateTransactionDto;
import com.example.cqrs_demo.core.entities.Status;
import com.example.cqrs_demo.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionCommandController {
    private final CommandGateway commandGateway;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTransaction(@Valid @RequestBody CreateTransactionDto createTransactionDto) {
        var date = new Date();
        final CreateTransactionCommand createTransactionCommand = CreateTransactionCommand.builder()
                .transactionRef(UUID.randomUUID().toString()).amount(createTransactionDto.getAmount())
                .status(Status.NEW).maskedPan(AppUtils.createMaskedPan()).status(Status.NEW)
                .date(new Timestamp(date.getTime()))
                .id(UUID.randomUUID().toString()).build();
        String returnedValue = commandGateway.sendAndWait(createTransactionCommand);
        return ResponseEntity.ok(returnedValue);
    }
}

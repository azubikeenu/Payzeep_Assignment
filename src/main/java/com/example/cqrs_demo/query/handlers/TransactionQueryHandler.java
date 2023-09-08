package com.example.cqrs_demo.query.handlers;

import com.example.cqrs_demo.core.entities.Transaction;
import com.example.cqrs_demo.core.repository.TransactionRepository;
import com.example.cqrs_demo.query.FindTransactionsQuery;
import com.example.cqrs_demo.query.rest.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionQueryHandler {

    final TransactionRepository transactionRepository;

    @QueryHandler
    public List<TransactionResponse> findProducts(FindTransactionsQuery findTransactionsQuery) {
        final List<Transaction> storedTransactions = transactionRepository.findAll();
        List<TransactionResponse> transactions = new ArrayList<>();
        for (Transaction transaction : storedTransactions) {
            TransactionResponse transactionResponse = new TransactionResponse();
            BeanUtils.copyProperties(transaction, transactionResponse);
            transactions.add(transactionResponse);
        }
        return transactions;
    }

}

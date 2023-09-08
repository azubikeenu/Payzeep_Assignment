package com.example.cqrs_demo.query.rest;

import com.example.cqrs_demo.query.FindTransactionsQuery;
import com.example.cqrs_demo.query.rest.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionQueryController {
    private final QueryGateway queryGateway;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionResponse> getTransactions() {
        FindTransactionsQuery findTransactionsQuery = new FindTransactionsQuery();
        // this dispatches a query to the query bus
        return queryGateway.
                query(findTransactionsQuery, ResponseTypes.multipleInstancesOf(TransactionResponse.class)).join();
    }


}

package com.example.cqrs_demo.core.repository;

import com.example.cqrs_demo.core.entities.TransactionLookup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLookupRepository extends JpaRepository<TransactionLookup , String> {
}

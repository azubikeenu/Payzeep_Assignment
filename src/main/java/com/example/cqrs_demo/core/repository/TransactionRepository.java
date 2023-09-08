package com.example.cqrs_demo.core.repository;

import com.example.cqrs_demo.core.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , String> {
}

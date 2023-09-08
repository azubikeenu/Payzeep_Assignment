package com.example.cqrs_demo.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

/*
This class is used to set base consistency between the query database and the event store
since the transactionId is a unique Identified the db , they are persisted
on transaction creation instance , this entity is only exclusive to the command module
 And primarily serves to check if a transaction with the same id  already exists in the database
**/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "transactionLookup")
public class TransactionLookup implements Serializable {
    @Id
    private String id;

}

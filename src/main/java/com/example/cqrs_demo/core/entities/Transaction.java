package com.example.cqrs_demo.core.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Transaction  implements Serializable {
    @Serial
    private static final long serialVersionUID = 2233999388399L;
    @Id
    @Column(unique = true)
    private String id;

    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    private String maskedPan;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String transactionRef;

}

package com.assessment.icinbank.transactions;

import com.assessment.icinbank.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;
    private LocalDate date;
    private LocalTime time;


    public Transaction(Long amount,
                       LocalDate date,
                       LocalTime time) {
        this.amount = amount;
        this.date = date;
        this.time = time;
    }
}

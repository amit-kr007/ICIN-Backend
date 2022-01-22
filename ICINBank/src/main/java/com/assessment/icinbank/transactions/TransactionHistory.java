package com.assessment.icinbank.transactions;

import com.assessment.icinbank.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Data
public class TransactionHistory extends Transaction{

    private String accountNo;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public TransactionHistory(Long amount,
                              LocalDate date,
                              LocalTime time,
                              String accountNo,
                              User user,
                              TransactionType transactionType) {
                            super(amount, date, time);
                            this.accountNo = accountNo;
                            this.user = user;
                            this.transactionType = transactionType;
    }

}

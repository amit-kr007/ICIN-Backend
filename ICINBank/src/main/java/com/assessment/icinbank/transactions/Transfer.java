package com.assessment.icinbank.transactions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
public class Transfer extends Transaction{

    private String senderAccountNo;
    private String senderUsername;
    private String receiverAccountNo;
    private String receiverUsername;

    public Transfer(Long amount,
                    LocalDate date,
                    LocalTime time,
                    String senderAccountNo,
                    String senderUsername,
                    String receiverAccountNo,
                    String receiverUsername) {
        super(amount, date, time);
        this.senderAccountNo = senderAccountNo;
        this.senderUsername = senderUsername;
        this.receiverAccountNo = receiverAccountNo;
        this.receiverUsername = receiverUsername;
    }
}

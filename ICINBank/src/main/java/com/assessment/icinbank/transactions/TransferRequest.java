package com.assessment.icinbank.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private Long amount;
    private Long senderId;
    private String receiverAccountNo;
    private String receiverUsername;
    private String senderAccountType;

}

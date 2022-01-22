package com.assessment.icinbank.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionHistoryRequest {
    private Long amount;
    private Long id;
}

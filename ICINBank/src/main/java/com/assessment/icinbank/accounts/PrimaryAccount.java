package com.assessment.icinbank.accounts;

import com.assessment.icinbank.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "primary_accounts")
@NoArgsConstructor
public class PrimaryAccount extends Account{

    public PrimaryAccount(Long balance,
                          String accountNo,
                          User user) {
        super(balance, accountNo, user);
    }

}

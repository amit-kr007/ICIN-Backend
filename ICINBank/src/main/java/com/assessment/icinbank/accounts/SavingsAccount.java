package com.assessment.icinbank.accounts;

import com.assessment.icinbank.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "savings_account")
@NoArgsConstructor
@Data
public class SavingsAccount extends Account{

    @JsonView(AccountView.Savings.class)
    @Column(name = "interest_rate")
    private Integer rate;

    public SavingsAccount(Long balance,
                          String accountNo,
                          User user,
                          Integer rate) {
        super(balance, accountNo, user);
        this.rate = rate;
    }


}

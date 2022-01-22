package com.assessment.icinbank.accounts;
import com.assessment.icinbank.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(AccountView.Base.class)
    private Long id;

    @JsonView(AccountView.Base.class)
    private Long balance;

    @JsonView(AccountView.Base.class)
    @Column(name = "account_no",unique = true)
    private String accountNo;

    @Getter(AccessLevel.NONE)
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public Account(Long balance, String accountNo, User user) {
        this.balance = balance;
        this.accountNo = accountNo;
        this.user = user;
    }

}

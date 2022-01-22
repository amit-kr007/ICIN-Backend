package com.assessment.icinbank.checkbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "checkbooks")
@NoArgsConstructor
@Data
public class CheckBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private CheckBookType accountType;

    @Column(name = "status")
    private Boolean checkBookStatus;

    public CheckBook(String username,
                     Long userId,
                     CheckBookType accountType,
                     Boolean checkBookStatus) {
        this.username = username;
        this.userId = userId;
        this.accountType = accountType;
        this.checkBookStatus = checkBookStatus;
    }
}

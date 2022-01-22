package com.assessment.icinbank.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}

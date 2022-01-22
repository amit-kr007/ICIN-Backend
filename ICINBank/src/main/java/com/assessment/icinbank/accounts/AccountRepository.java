package com.assessment.icinbank.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface AccountRepository extends JpaRepository<Account,Long> {
}

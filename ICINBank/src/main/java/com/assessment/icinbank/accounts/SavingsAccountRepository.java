package com.assessment.icinbank.accounts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends AccountRepository{

    @Query(value = "SELECT * FROM savings_account WHERE user_id=:id",nativeQuery = true)
    Optional<SavingsAccount> findByUserId(@Param("id") Long id);

}

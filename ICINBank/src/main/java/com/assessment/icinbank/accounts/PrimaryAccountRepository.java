package com.assessment.icinbank.accounts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrimaryAccountRepository extends AccountRepository{

    @Query(value = "SELECT * FROM primary_accounts WHERE user_id=:id",nativeQuery = true)
    Optional<PrimaryAccount> findByUserId(@Param("id") Long id);
}

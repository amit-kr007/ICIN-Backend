package com.assessment.icinbank.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PrimaryAccountRepository primaryAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    public void createPrimaryAccount(PrimaryAccount primaryAccount){
        primaryAccountRepository.save(primaryAccount);
    }

    public void createSavingsAccount(SavingsAccount savingsAccount){
        savingsAccountRepository.save(savingsAccount);
    }

    public Optional<PrimaryAccount> getPrimaryAccountDetails(Long id){
        return primaryAccountRepository.findByUserId(id);
    }


    public Optional<SavingsAccount> getSavingsAccountDetails(Long id) {
        return savingsAccountRepository.findByUserId(id);
    }
}

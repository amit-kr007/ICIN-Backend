package com.assessment.icinbank.transactions;

import com.assessment.icinbank.accounts.PrimaryAccount;
import com.assessment.icinbank.accounts.PrimaryAccountRepository;
import com.assessment.icinbank.accounts.SavingsAccount;
import com.assessment.icinbank.accounts.SavingsAccountRepository;
import com.assessment.icinbank.users.User;
import com.assessment.icinbank.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final PrimaryAccountRepository primaryAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    public void depositToPrimary(Long amount,Long id){
        User user = userRepository.findByUserId(id);
        PrimaryAccount userAccount = user.getPrimaryAccount();
        Long newAmount = userAccount.getBalance() + amount;
        userAccount.setBalance(newAmount);
        primaryAccountRepository.save(userAccount);


        transactionHistoryRepository.save(new TransactionHistory(
                amount,
                LocalDate.now(),
                LocalTime.now(),
                userAccount.getAccountNo(),
                user,
                TransactionType.deposit
        ));
    }

    public void withdrawFromPrimary(Long amount,Long id) throws Exception {
        User user = userRepository.findByUserId(id);
        PrimaryAccount userAccount = user.getPrimaryAccount();
        Long oldAmount = userAccount.getBalance();
        if(oldAmount < amount || oldAmount == 0){
            throw new Exception("Amount can not be withdrawn");
        }

        else{
            Long newAmount = oldAmount - amount;
            userAccount.setBalance(newAmount);
            primaryAccountRepository.save(userAccount);

            transactionHistoryRepository.save(new TransactionHistory(
                    amount,
                    LocalDate.now(),
                    LocalTime.now(),
                    userAccount.getAccountNo(),
                    user,
                    TransactionType.withdrawal
            ));

        }


    }

    public void depositToSavings(Long amount,Long id){
        User user = userRepository.findByUserId(id);
        SavingsAccount userAccount = user.getSavingsAccount();
        Long newAmount = userAccount.getBalance() + amount;
        userAccount.setBalance(newAmount);
        savingsAccountRepository.save(userAccount);


        transactionHistoryRepository.save(new TransactionHistory(
                amount,
                LocalDate.now(),
                LocalTime.now(),
                userAccount.getAccountNo(),
                user,
                TransactionType.deposit
        ));
    }

    public void withdrawFromSavings(Long amount,Long id) throws Exception {
        User user = userRepository.findByUserId(id);
        SavingsAccount userAccount = user.getSavingsAccount();
        Long oldAmount = userAccount.getBalance();
        if(oldAmount < amount || oldAmount == 0){
            throw new Exception("Amount can not be withdrawn");
        }

        else{
            Long newAmount = oldAmount - amount;
            userAccount.setBalance(newAmount);
            savingsAccountRepository.save(userAccount);

            transactionHistoryRepository.save(new TransactionHistory(
                    amount,
                    LocalDate.now(),
                    LocalTime.now(),
                    userAccount.getAccountNo(),
                    user,
                    TransactionType.withdrawal
            ));

        }


    }

    public void transfer(TransferRequest transferRequest) throws Exception {
        Optional<User> receiver = userRepository.findUserByEmail(
                transferRequest.getReceiverUsername()
        );
        String accountType =  transferRequest.getSenderAccountType();
        String receiverPrimaryAccount = receiver.get().getPrimaryAccount().getAccountNo();
        String receiverSavingsAccount = receiver.get().getSavingsAccount().getAccountNo();

        System.out.println(accountType);
        if(accountType.equals("PRIMARY")){
            System.out.println("Inside PRIMARY");



            if(transferRequest.getReceiverAccountNo().equals(receiverPrimaryAccount)){
                depositToPrimary(transferRequest.getAmount(),receiver.get().getId());
                withdrawFromPrimary(transferRequest.getAmount(), transferRequest.getSenderId());
            }

            else if(transferRequest.getReceiverAccountNo().equals(receiverSavingsAccount)){
                depositToSavings(transferRequest.getAmount(),receiver.get().getId());
                withdrawFromPrimary(transferRequest.getAmount(), transferRequest.getSenderId());
            }

            else{
                throw new Exception("Account Number does not exist");
            }

        }

        else {
            System.out.println("inside SAVINGS");

            if(transferRequest.getReceiverAccountNo().equals(receiverPrimaryAccount)){
                System.out.println("Inside receiver Primary");
                depositToPrimary(transferRequest.getAmount(),receiver.get().getId());
                withdrawFromSavings(transferRequest.getAmount(),transferRequest.getSenderId());
            }

            else if(transferRequest.getReceiverAccountNo().equals(receiverSavingsAccount)){
                depositToSavings(transferRequest.getAmount(),receiver.get().getId());
                withdrawFromSavings(transferRequest.getAmount(),transferRequest.getSenderId());
            }

            else{
                throw new Exception("Account Number does not exist");
            }


        }

        User sender = userRepository.findByUserId(transferRequest.getSenderId());

        if(accountType.equals("PRIMARY"))
        {
            transferRepository.save(new Transfer(
                    transferRequest.getAmount(),
                    LocalDate.now(),
                    LocalTime.now(),
                    sender.getPrimaryAccount().getAccountNo(),
                    sender.getUsername(),
                    transferRequest.getReceiverAccountNo(),
                    transferRequest.getReceiverUsername()
            ));
        }
        else{
            transferRepository.save(new Transfer(
                    transferRequest.getAmount(),
                    LocalDate.now(),
                    LocalTime.now(),
                    sender.getSavingsAccount().getAccountNo(),
                    sender.getUsername(),
                    transferRequest.getReceiverAccountNo(),
                    transferRequest.getReceiverUsername()
            ));
        }



    }

}


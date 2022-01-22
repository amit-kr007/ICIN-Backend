package com.assessment.icinbank.users;

import com.assessment.icinbank.accounts.*;
import com.assessment.icinbank.checkbook.CheckBookRequest;
import com.assessment.icinbank.checkbook.CheckBookType;
import com.assessment.icinbank.transactions.TransactionHistory;
import com.assessment.icinbank.transactions.TransactionHistoryRequest;
import com.assessment.icinbank.transactions.TransactionService;
import com.assessment.icinbank.transactions.TransferRequest;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("/userprofile/{email}")
    public Optional<User> sendUserProfile(@PathVariable String email){ return userService.userProfile(email); }

    @GetMapping("/account/primary/{id}")
    @JsonView(AccountView.Base.class)
    public Optional<PrimaryAccount> getPrimaryAccountById(@PathVariable Long id){
        return accountService.getPrimaryAccountDetails(id);
    }

    @GetMapping("/account/savings/{id}")
    @JsonView(AccountView.Savings.class)
    public Optional<SavingsAccount> getSavingsAccountById(@PathVariable Long id){
        return accountService.getSavingsAccountDetails(id);
    }

    @PostMapping("/request/checkbook")
    public Integer requestCheckBook(@RequestBody CheckBookRequest checkBookRequest){
        Integer status;
        try{
            userService.requestCheckbook(checkBookRequest);
            status = 1;
        }
        catch (Exception e){
            System.out.println(e);
            status = 0;
        }

        return status;
    }

    @GetMapping("/checkbook/status/primary/{userId}")
    public Boolean getPrimaryCheckBookStatus(@PathVariable Long userId){
        return userService.getPrimaryCheckBookStatus(userId);
    }

    @GetMapping("/checkbook/status/savings/{userId}")
    public Boolean getSavingsCheckBookStatus(@PathVariable Long userId){
        return userService.getSavingsCheckBookStatus(userId);
    }


    @PostMapping("/deposit/primary")
    public void depositToPrimaryAccount(@RequestBody TransactionHistoryRequest transactionHistoryRequest){
            transactionService.depositToPrimary(
                    transactionHistoryRequest.getAmount(),
                    transactionHistoryRequest.getId());
    }

    @PostMapping("/withdraw/primary")
    public void withdrawFromPrimaryAccount(@RequestBody TransactionHistoryRequest transactionHistoryRequest) throws Exception {
        transactionService.withdrawFromPrimary(
                transactionHistoryRequest.getAmount(),
                transactionHistoryRequest.getId());
    }

    @PostMapping("/deposit/savings")
    public void depositToSavingsAccount(@RequestBody TransactionHistoryRequest transactionHistoryRequest){
        transactionService.depositToSavings(
                transactionHistoryRequest.getAmount(),
                transactionHistoryRequest.getId());
    }

    @PostMapping("/withdraw/savings")
    public void withdrawFromSavingsAccount(@RequestBody TransactionHistoryRequest transactionHistoryRequest) throws Exception {
        transactionService.withdrawFromSavings(
                transactionHistoryRequest.getAmount(),
                transactionHistoryRequest.getId());
    }

    @GetMapping("/transactions/{id}")
    public List<TransactionHistory> getAllTransactionsByUserId(@PathVariable Long id){
        return userService.getAllUserTransactions(id);
    }

    @PutMapping("/update/{id}")
    public int updateProfile(@PathVariable Long id, @RequestBody User user){
        int status;
        try{
            userService.updateUserProfile(id,user);
            status = 1;
        }
        catch (Exception e){
            status = -1;
            System.out.println(e);
        }

        return status;
    }


    @PostMapping("/transfer")
    public Integer makeTransfer(@RequestBody TransferRequest transferRequest) throws Exception {
        Integer status;
        try{
            transactionService.transfer(transferRequest);
            status = 1;
        }
        catch (Exception e){
            status = 0;
        }

        return status;
    }



}

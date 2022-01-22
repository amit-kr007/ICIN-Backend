package com.assessment.icinbank.users;

import com.assessment.icinbank.checkbook.CheckBook;
import com.assessment.icinbank.checkbook.CheckBookRepository;
import com.assessment.icinbank.checkbook.CheckBookRequest;
import com.assessment.icinbank.transactions.TransactionHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CheckBookRepository checkBookRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username with email %s not found",email)));

    }

    public void registerNewUser(User newUser){

        Optional<User> user = userRepository.findUserByEmail(newUser.getEmail());

        System.out.println(user);
        if(user.isPresent()){
            throw new IllegalStateException("Email already exists");
        }
            String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
            newUser.setPassword(encodedPassword);
            newUser.setEnabled(false);
            userRepository.save(newUser);

    }

    public Optional<User> userProfile(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> userProfiles(){
        return userRepository.findAllByUserRole("USER");
    }

    public void enableProfile(String email){
        User user = (User) loadUserByUsername(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void disableProfile(String email){
        User user = (User) loadUserByUsername(email);
        user.setEnabled(false);
        userRepository.save(user);
    }


    public void requestCheckbook(CheckBookRequest checkBookRequest) throws Exception {
        User user = userRepository.findByUserId(checkBookRequest.getUserId());
        CheckBook checkBook = checkBookRepository.findByUserIdAndType(checkBookRequest.getUserId(),checkBookRequest.getAccountType().name());
        if(checkBook == null) {
            if (user != null) {
                checkBookRepository.save(new CheckBook(
                        user.getUsername(),
                        user.getId(),
                        checkBookRequest.getAccountType(),
                        false
                ));
            }

        }

        else{
            throw new Exception("Checkbook request already made");
        }

    }

    public Boolean getPrimaryCheckBookStatus(Long userId){
           CheckBook checkBook = checkBookRepository.findByUserIdAndType(userId,"PRIMARY");
           if(checkBook == null){
               return false;
           }
           return checkBook.getCheckBookStatus();
    }


    public List<TransactionHistory> getAllUserTransactions(Long id) {
        User user = userRepository.findByUserId(id);
        return user.getTransactionHistoryList();
    }

    public Boolean getSavingsCheckBookStatus(Long userId) {
        CheckBook checkBook = checkBookRepository.findByUserIdAndType(userId,"SAVINGS");
        if(checkBook == null){
            return false;
        }
        return checkBook.getCheckBookStatus();
    }

    public void updateUserProfile(Long id, User user) {
        User newUser = userRepository.findByUserId(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setPhoneNo(user.getPhoneNo());
        userRepository.save(newUser);

    }
}

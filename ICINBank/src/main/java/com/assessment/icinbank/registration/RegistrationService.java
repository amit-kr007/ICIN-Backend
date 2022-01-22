package com.assessment.icinbank.registration;

import com.assessment.icinbank.users.User;
import com.assessment.icinbank.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public void registerUser(User user){
        userService.registerNewUser(user);
    }

}

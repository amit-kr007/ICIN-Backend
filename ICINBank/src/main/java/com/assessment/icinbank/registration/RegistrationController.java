package com.assessment.icinbank.registration;

import com.assessment.icinbank.users.User;
import com.assessment.icinbank.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/register")
    public void registration(@RequestBody User user){registrationService.registerUser(user);}

}

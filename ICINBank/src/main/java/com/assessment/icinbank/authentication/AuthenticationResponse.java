package com.assessment.icinbank.authentication;

import com.assessment.icinbank.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private User user;

}

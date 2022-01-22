package com.assessment.icinbank.authentication;

import com.assessment.icinbank.users.User;
import com.assessment.icinbank.users.UserService;
import com.assessment.icinbank.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthenticationController {

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/create_token")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Password");
        }
        catch(DisabledException e){
            throw new DisabledException("User is disabled");
        }

        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,(User) userDetails));

    }

    @GetMapping("/hello")
    public String example(){
        return "Hello World";
    }
}

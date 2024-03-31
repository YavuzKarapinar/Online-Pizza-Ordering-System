package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.LoginRequest;
import me.jazzy.opos.dto.RegisterRequest;
import me.jazzy.opos.exception.notfound.UserNotFoundException;
import me.jazzy.opos.model.ResponseBody;
import me.jazzy.opos.model.Role;
import me.jazzy.opos.model.User;
import me.jazzy.opos.security.jwt.JwtGenerator;
import me.jazzy.opos.validator.EmailValidation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final EmailValidation emailValidation;

    public ResponseBody register(RegisterRequest request) {

        boolean isValid = emailValidation.test(request.getEmail());

        if(!isValid)
            throw new UserNotFoundException("There is no such email");

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getContactNumber(),
                Role.USER);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);

        return new ResponseBody(
                HttpStatus.CREATED.value(),
                "User created.",
                LocalDateTime.now()
        );
    }

    public ResponseBody login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                            request.getEmail(),
                                            request.getPassword())
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(request.getEmail());

        return new ResponseBody(
                HttpStatus.OK.value(),
                token,
                LocalDateTime.now()
        );
    }
}
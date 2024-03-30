package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.ChangePasswordRequest;
import me.jazzy.opos.dto.VerifyCodeDto;
import me.jazzy.opos.dto.ResetPasswordRequestDto;
import me.jazzy.opos.model.ResetPassword;
import me.jazzy.opos.model.ResponseBody;
import me.jazzy.opos.model.User;
import me.jazzy.opos.repository.ResetPasswordRepository;
import me.jazzy.opos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private final ResetPasswordRepository resetPasswordRepository;
    private final EmailSenderService emailSenderService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResponseBody resetCodeGenerator(ResetPasswordRequestDto resetPasswordRequestDto) {

        UUID uuid = UUID.randomUUID();
        int sixDigitCode = new Random().nextInt(100_000, 999_999);
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * 3); // 3 min
        User user = userService.getUserByUsername(resetPasswordRequestDto.getEmail());
        ResetPassword resetPassword =
                new ResetPassword(uuid, user, sixDigitCode, expireDate, false);

        resetPasswordRepository.save(resetPassword);

        emailSenderService.sendEmailTo(user.getEmail(),
                                "Reset Password",
                                "Your single-use code: " + sixDigitCode);

        return new ResponseBody(
                HttpStatus.OK.value(),
                "Reset Code Sent.",
                LocalDateTime.now()
        );
    }

    public ResponseBody resetPassword(ChangePasswordRequest request) {

        User user = userService.getUserByUsername(request.getEmail());

        if(!request.getNewPassword().equals(request.getNewPasswordConfirm()))
            throw new RuntimeException("Passwords not same");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return new ResponseBody(
                HttpStatus.OK.value(),
                "Password successfully changed.",
                LocalDateTime.now()
        );
    }

    public ResponseBody verifyCode(VerifyCodeDto verifyCodeDto) {
        ResetPassword resetPassword = resetPasswordRepository.
                findResetPasswordByEmailAndCode(verifyCodeDto.getEmail(), verifyCodeDto.getCode());

        if(isExpired(resetPassword))
            throw new RuntimeException("Reset Password expired.");

        if(resetPassword.isEnabled())
            throw new RuntimeException("Reset code already used.");

        resetPassword.setEnabled(true);

        resetPasswordRepository.save(resetPassword);

        return new ResponseBody(
                HttpStatus.OK.value(),
                "Code verified.",
                LocalDateTime.now()
        );
    }

    private boolean isExpired(ResetPassword resetPassword) {
        return resetPassword.getExpireDate().before(new Date());
    }

}
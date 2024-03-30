package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.*;
import me.jazzy.opos.model.ResponseBody;
import me.jazzy.opos.service.AuthService;
import me.jazzy.opos.service.ResetPasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ResetPasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<ResponseBody> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseBody> resetPassword(@RequestBody ResetPasswordRequestDto request) {
        return new ResponseEntity<>(passwordService.resetCodeGenerator(request), HttpStatus.OK);
    }

    @PostMapping("/verifyResetCode")
    public ResponseEntity<ResponseBody> verifyCode(@RequestBody VerifyCodeDto request) {
        return new ResponseEntity<>(passwordService.verifyCode(request), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseBody> changePassword(@RequestBody ChangePasswordRequest request) {
        return new ResponseEntity<>(passwordService.resetPassword(request), HttpStatus.OK);
    }
}
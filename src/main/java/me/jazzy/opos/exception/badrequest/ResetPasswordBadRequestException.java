package me.jazzy.opos.exception.badrequest;

import me.jazzy.opos.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ResetPasswordBadRequestException extends BaseException {
    public ResetPasswordBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

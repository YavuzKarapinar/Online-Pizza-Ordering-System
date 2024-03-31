package me.jazzy.opos.exception.badrequest;

import me.jazzy.opos.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserBadRequestException extends BaseException {
    public UserBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

package me.jazzy.opos.exception.notfound;

import me.jazzy.opos.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CartNotFoundException extends BaseException {
    public CartNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

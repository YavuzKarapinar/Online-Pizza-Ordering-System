package me.jazzy.opos.exception.notfound;

import me.jazzy.opos.exception.BaseException;
import org.springframework.http.HttpStatus;

public class PizzaNotFoundException extends BaseException {
    public PizzaNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

package me.jazzy.opos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import me.jazzy.opos.model.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<ResponseBody> exceptionHandling(BaseException baseException) {
         ResponseBody responseBody = new ResponseBody(
                 baseException.getHttpStatus().value(),
                 baseException.getMessage(),
                 LocalDateTime.now()
         );

        return new ResponseEntity<>(responseBody, baseException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseBody> exceptionHandling(Throwable throwable) {
        ResponseBody responseBody = new ResponseBody(
                HttpStatus.BAD_REQUEST.value(),
                throwable.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
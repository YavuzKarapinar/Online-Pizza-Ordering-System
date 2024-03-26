package me.jazzy.opos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBody {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ResponseBody(int status,
                        String message,
                        LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
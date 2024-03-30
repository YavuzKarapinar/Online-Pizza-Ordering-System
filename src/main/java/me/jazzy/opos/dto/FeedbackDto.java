package me.jazzy.opos.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private Long pizzaId;
    private String email;
    private String subject;
    private String message;
}
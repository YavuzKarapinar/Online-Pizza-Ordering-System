package me.jazzy.opos.dto;

import lombok.Data;

@Data
public class VerifyCodeDto {
    private String email;
    private int code;
}
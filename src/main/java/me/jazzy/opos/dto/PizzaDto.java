package me.jazzy.opos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PizzaDto {
    private String name;
    private BigInteger money;
    private Long ingredientId;
    private Long categoryId;
}
package me.jazzy.opos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PizzaDto {
    private String name;
    private double money;
    private Long ingredientId;
    private Long categoryId;
}
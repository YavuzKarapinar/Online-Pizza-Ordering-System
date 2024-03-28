package me.jazzy.opos.dto;

import lombok.Data;
import me.jazzy.opos.model.CartItem;

import java.util.List;

@Data
public class CartDto {
    private List<CartItem> cartItems;
}
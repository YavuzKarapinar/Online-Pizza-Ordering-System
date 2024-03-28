package me.jazzy.opos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "carts")
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<CartItem> cartItems;
    private double totalCost;

    public Cart(List<CartItem> cartItems,
                double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }
}
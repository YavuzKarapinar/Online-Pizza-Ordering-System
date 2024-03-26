package me.jazzy.opos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name = "pizza_id")
    private List<Pizza> pizza;
    private BigInteger costPerUnit;
    private BigInteger totalCost;

}
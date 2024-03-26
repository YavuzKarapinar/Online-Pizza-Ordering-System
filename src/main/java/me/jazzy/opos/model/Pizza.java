package me.jazzy.opos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "pizzas")
@Data
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigInteger money;
    @OneToMany
    @JoinColumn(name = "ingredients_id")
    private List<Ingredients> ingredients;
    private String image;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Pizza(String name,
                 BigInteger money,
                 List<Ingredients> ingredients,
                 String image,
                 Category category) {
        this.name = name;
        this.money = money;
        this.ingredients = ingredients;
        this.image = image;
        this.category = category;
    }
}
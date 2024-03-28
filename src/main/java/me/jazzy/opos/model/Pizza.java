package me.jazzy.opos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double money;
    @OneToMany
    private List<Ingredient> ingredients;
    private String image;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Pizza(String name,
                 double money,
                 List<Ingredient> ingredients,
                 Category category) {
        this.name = name;
        this.money = money;
        this.ingredients = ingredients;
        this.category = category;
    }
}
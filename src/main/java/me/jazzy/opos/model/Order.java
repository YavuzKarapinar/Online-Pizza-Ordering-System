package me.jazzy.opos.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Order(User user,
                 OrderStatus orderStatus,
                 LocalDateTime orderDate,
                 Cart cart) {
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.cart = cart;
    }
}
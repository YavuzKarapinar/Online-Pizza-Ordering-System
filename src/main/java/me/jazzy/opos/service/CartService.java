package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.CartDto;
import me.jazzy.opos.model.Cart;
import me.jazzy.opos.model.CartItem;
import me.jazzy.opos.model.Pizza;
import me.jazzy.opos.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final PizzaService pizzaService;
    private final CartItemService cartItemService;

    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("There is no such cart."));
    }

    public Cart saveCart(CartDto cartDto) {

        List<CartItem> cartItem = cartDto.getCartItems();

        double total = 0;

        for(CartItem item : cartItem) {
            cartItemService.saveCartItem(item);
            Pizza pizza = pizzaService.getById(item.getPizzaId());
            total += pizza.getMoney() * item.getQuantity();
        }

        Cart cart = new Cart(
                cartItem,
                total
        );

        cartRepository.save(cart);

        return cart;
    }

    public Cart updateCart(Cart cart) {
        getCartById(cart.getId());

        cartRepository.save(cart);

        return getCartById(cart.getId());
    }
}
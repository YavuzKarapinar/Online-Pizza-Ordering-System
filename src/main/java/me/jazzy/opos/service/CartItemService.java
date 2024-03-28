package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.CartItem;
import me.jazzy.opos.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

}
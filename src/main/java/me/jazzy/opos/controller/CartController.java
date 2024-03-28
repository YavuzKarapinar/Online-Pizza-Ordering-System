package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.CartDto;
import me.jazzy.opos.model.Cart;
import me.jazzy.opos.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> saveCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.saveCart(cartDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.OK);
    }
}

package com.aam.onlineshopping.controller;

import com.aam.onlineshopping.model.Cart;
import com.aam.onlineshopping.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long accountId) {
        Cart cart = cartService.getCartByUserId(accountId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{accountId}/add/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long accountId, @PathVariable Long productId) {
        Cart cart = cartService.addProductToCart(accountId, productId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{accountId}/remove/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long accountId, @PathVariable Long productId) {
        Cart cart = cartService.removeProductFromCart(accountId, productId);
        return ResponseEntity.ok(cart);
    }
}

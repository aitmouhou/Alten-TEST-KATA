package com.aam.onlineshopping.controller;

import com.aam.onlineshopping.model.WishProductList;
import com.aam.onlineshopping.service.WishProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishProduct")
public class WishProductController {

    private final WishProductService wishlistService;

    public WishProductController(WishProductService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WishProductList> getWishlist(@PathVariable Long userId) {
        WishProductList wishlist = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<WishProductList> addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        WishProductList wishlist = wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/{userId}/remove/{productId}")
    public ResponseEntity<WishProductList> removeProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        WishProductList wishlist = wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.ok(wishlist);
    }
}

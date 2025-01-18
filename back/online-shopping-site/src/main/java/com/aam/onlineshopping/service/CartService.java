package com.aam.onlineshopping.service;

import com.aam.onlineshopping.model.Cart;
import com.aam.onlineshopping.model.Product;
import com.aam.onlineshopping.repository.CartRepository;
import com.aam.onlineshopping.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByAccountId(userId);
    }

    public Cart addProductToCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByAccountId(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByAccountId(userId);
        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        return cartRepository.save(cart);
    }
}

package com.aam.onlineshopping.service;

import com.aam.onlineshopping.model.Product;
import com.aam.onlineshopping.model.WishProductList;
import com.aam.onlineshopping.repository.ProductRepository;
import com.aam.onlineshopping.repository.WishProductRepository;
import org.springframework.stereotype.Service;

@Service
public class WishProductService {

    private final WishProductRepository wishlistRepository;
    private final ProductRepository productRepository;

    public WishProductService(WishProductRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public WishProductList getWishlistByUserId(Long userId) {
        return wishlistRepository.findByAccountId(userId);
    }

    public WishProductList addProductToWishlist(Long userId, Long productId) {
        WishProductList wishlist = wishlistRepository.findByAccountId(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }

    public WishProductList removeProductFromWishlist(Long userId, Long productId) {
        WishProductList wishlist = wishlistRepository. findByAccountId(userId);
        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));
        return wishlistRepository.save(wishlist);
    }
}

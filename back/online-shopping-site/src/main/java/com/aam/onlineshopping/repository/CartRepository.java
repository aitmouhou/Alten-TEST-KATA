package com.aam.onlineshopping.repository;

import com.aam.onlineshopping.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByAccountId(Long accountId);
}

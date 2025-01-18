package com.aam.onlineshopping.repository;

import com.aam.onlineshopping.model.WishProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishProductRepository extends JpaRepository<WishProductList, Long> {
    WishProductList findByAccountId(Long accountId);
}
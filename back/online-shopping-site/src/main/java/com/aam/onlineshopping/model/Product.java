package com.aam.onlineshopping.model;

import com.aam.onlineshopping.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;

    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    private int rating;

    private Instant createdAt;
    private Instant updatedAt;


}

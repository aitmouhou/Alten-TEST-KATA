package com.aam.onlineshopping.dto;

import com.aam.onlineshopping.enums.InventoryStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String image;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Price is required")
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private Integer rating;
    private Instant createdAt;
    private Instant updatedAt;
}
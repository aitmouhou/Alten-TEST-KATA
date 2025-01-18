package com.aam.onlineshopping.service;

import com.aam.onlineshopping.dto.ProductDTO;
import com.aam.onlineshopping.mappers.ProductMapper;
import com.aam.onlineshopping.model.Product;
import com.aam.onlineshopping.repository.ProductRepository;
import com.aam.onlineshopping.security.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return !"admin@admin.com".equals(userEmail);
    }

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws AccessDeniedException {
        if (isAdmin()) {
            throw new AccessDeniedException("ProductService :: createProduct : Only the admin can create new product");
        }
        Product product = productMapper.toEntity(productDTO);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }

    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO) throws AccessDeniedException {
        if (isAdmin()) {
            throw new AccessDeniedException("ProductService :: createProduct : Only the admin can create new product");
        }
        return productRepository.findById(id).map(product -> {
            product.setCode(productDTO.getCode());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setImage(productDTO.getImage());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());
            product.setInternalReference(productDTO.getInternalReference());
            product.setShellId(productDTO.getShellId());
            product.setInventoryStatus(productDTO.getInventoryStatus());
            product.setRating(productDTO.getRating());
            product.setUpdatedAt(Instant.now());
            Product updatedProduct = productRepository.save(product);
            return productMapper.toDTO(updatedProduct);
        });
    }

    public void deleteProduct(Long id) throws AccessDeniedException {
        if (isAdmin()) {
            throw new AccessDeniedException("ProductService :: deleteProduct : Only the admin can delete product");
        }
        productRepository.deleteById(id);
    }
}
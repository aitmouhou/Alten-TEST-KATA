package com.aam.onlineshopping;

import com.aam.onlineshopping.dto.ProductDTO;
import com.aam.onlineshopping.enums.InventoryStatus;
import com.aam.onlineshopping.mappers.ProductMapper;
import com.aam.onlineshopping.model.Product;
import com.aam.onlineshopping.repository.ProductRepository;
import com.aam.onlineshopping.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(30.00);
        productDTO.setCode("RFEF");
        productDTO.setImage("IMAGE");
        productDTO.setCategory("A");
        productDTO.setQuantity(30);
        productDTO.setInternalReference("HSK");
        productDTO.setShellId(333L);
        productDTO.setInventoryStatus(InventoryStatus.LOWSTOCK);
        productDTO.setRating(5);
        productDTO.setUpdatedAt(Instant.now());
    }

    @Test
    void getAllProducts_ShouldReturnListOfProductDTOs() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);
        List<ProductDTO> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productDTO, result.get(0));
    }

    @Test
    void getProductById_ShouldReturnProductDTO_WhenProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);
        Optional<ProductDTO> result = productService.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals(productDTO, result.get());
    }

    @Test
    void getProductById_ShouldReturnEmptyOptional_WhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ProductDTO> result = productService.getProductById(1L);
        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void createProduct_ShouldThrowAccessDeniedException_WhenUserIsNotAdmin() throws AccessDeniedException {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@test.com");
        SecurityContextHolder.setContext(securityContext);
        assertThrows(AccessDeniedException.class, () -> productService.createProduct(productDTO));
    }

    @Test
    void createProduct_ShouldReturnProductDTO_WhenUserIsAdmin() throws AccessDeniedException {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin@admin.com");
        SecurityContextHolder.setContext(securityContext);
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);
        ProductDTO result = productService.createProduct(productDTO);
        assertNotNull(result);
        assertEquals(productDTO, result);
    }

    @Test
    void updateProduct_ShouldThrowAccessDeniedException_WhenUserIsNotAdmin() throws AccessDeniedException {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@test.com");
        SecurityContextHolder.setContext(securityContext);
        assertThrows(AccessDeniedException.class, () -> productService.updateProduct(1L, productDTO));
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProductDTO_WhenUserIsAdmin() throws AccessDeniedException {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin@admin.com");
        SecurityContextHolder.setContext(securityContext);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);
        Optional<ProductDTO> result = productService.updateProduct(1L, productDTO);
        assertTrue(result.isPresent());
        assertEquals(productDTO, result.get());
    }




}

package com.aam.onlineshopping.mappers;

import com.aam.onlineshopping.dto.ProductDTO;
import com.aam.onlineshopping.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Convertir une entité Product en ProductDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "internalReference", target = "internalReference")
    @Mapping(source = "shellId", target = "shellId")
    @Mapping(source = "inventoryStatus", target = "inventoryStatus")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ProductDTO toDTO(Product product);

    // Convertir un ProductDTO en entité Product
    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "internalReference", target = "internalReference")
    @Mapping(source = "shellId", target = "shellId")
    @Mapping(source = "inventoryStatus", target = "inventoryStatus")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDTOList(List<Product> products);

    List<Product> toEntityList(List<ProductDTO> productDTOs);
}

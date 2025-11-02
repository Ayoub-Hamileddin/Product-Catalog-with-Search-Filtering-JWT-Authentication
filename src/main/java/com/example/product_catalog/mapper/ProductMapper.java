package com.example.product_catalog.mapper;



import com.example.product_catalog.entity.Category;
import com.example.product_catalog.entity.Product;
import com.example.product_catalog.entity.User;
import com.example.product_catalog.payload.dto.ProductDTO;
import com.example.product_catalog.payload.dto.UserDTO;

public class ProductMapper {

    public static ProductDTO ToDTO(Product product){
        return ProductDTO.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .rating(product.getRating())
            .inStock(product.getInStock())
            .category(product.getCategory())

        .build();
    }


    public static Product ToEntity(ProductDTO productDTO,Category category){
        return Product.builder()
            .id(productDTO.getId())
            .name(productDTO.getName())
            .description(productDTO.getDescription())
            .price(productDTO.getPrice())
            .rating(productDTO.getRating())
            .inStock(productDTO.getInStock())
            .category(category)

        .build();
    }

}

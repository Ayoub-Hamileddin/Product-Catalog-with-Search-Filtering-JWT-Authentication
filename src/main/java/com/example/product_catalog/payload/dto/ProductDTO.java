package com.example.product_catalog.payload.dto;

import com.example.product_catalog.entity.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id ;
    private String name ;
    private String description ;
    private Double price ;
    private Double rating ;
    private Boolean inStock ;
    private Category category ;
}

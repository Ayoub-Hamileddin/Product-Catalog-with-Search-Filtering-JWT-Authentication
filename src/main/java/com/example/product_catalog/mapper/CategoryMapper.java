package com.example.product_catalog.mapper;


import com.example.product_catalog.entity.Category;
import com.example.product_catalog.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDTo(Category category){
            return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())

            .build();
    }
    public static Category ToEntity(CategoryDto categoryDto){
            return Category.builder()
                .name(categoryDto.getName())
            .build();
    }
}

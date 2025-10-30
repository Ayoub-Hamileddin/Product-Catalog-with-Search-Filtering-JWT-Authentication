package com.example.product_catalog.service;

import java.util.List;

import com.example.product_catalog.payload.dto.CategoryDto;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById();
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long CategoryId,CategoryDto categoryDto);
    void deleteCatgory();
}

package com.example.product_catalog.service;

import java.util.List;

import com.example.product_catalog.payload.dto.CategoryDto;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long categoryId) throws Exception;
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long CategoryId,CategoryDto categoryDto) throws Exception;
    void deleteCatgory(Long categoryId) throws Exception;
}

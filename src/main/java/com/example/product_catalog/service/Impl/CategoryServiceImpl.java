package com.example.product_catalog.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product_catalog.entity.Category;
import com.example.product_catalog.mapper.CategoryMapper;
import com.example.product_catalog.payload.dto.CategoryDto;
import com.example.product_catalog.repository.CategoryRepository;
import com.example.product_catalog.service.CategoryService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
       List<Category> categories=categoryRepository.findAll();
       return categories.stream()
                    .map(category->CategoryMapper.toDTo(category) )
                    .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
            ()->new Exception("category not found")
        );
        return CategoryMapper.toDTo(category);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category newCategory=CategoryMapper.ToEntity(categoryDto);

        Category savedCategory=categoryRepository.save(newCategory);

        return CategoryMapper.toDTo(newCategory);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) throws Exception {

        Category category=categoryRepository.findById(categoryId).orElseThrow(
            ()->new Exception("category not found")
        );
        category.setName(categoryDto.getName());

        Category savedCategory= categoryRepository.save(category);

        return CategoryMapper.toDTo(savedCategory);


    }

    @Override
    public void deleteCatgory(Long categoryId) throws Exception {
          Category category=categoryRepository.findById(categoryId).orElseThrow(
            ()->new Exception("category not found")
        );  
        categoryRepository.delete(category);
        
    }

}

package com.example.product_catalog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.product_catalog.payload.dto.CategoryDto;
import com.example.product_catalog.payload.response.ApiResponse;
import com.example.product_catalog.service.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
        private final CategoryService categoryService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<CategoryDto> createCatgory(@RequestBody CategoryDto categoryDto) {
    
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));

    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,@RequestBody CategoryDto categoryDto) throws Exception {
    
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ApiResponse deleteCategory(@PathVariable("id") Long id) throws Exception {
    
       categoryService.deleteCatgory(id);
       return ApiResponse.builder()
                .message("the category with this id:" +id + " deleted")
       .build();


    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws Exception {
    
       return ResponseEntity.ok(categoryService.getAllCategories());


    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) throws Exception {
    
       return ResponseEntity.ok(categoryService.getCategoryById(id));


    }
        
}

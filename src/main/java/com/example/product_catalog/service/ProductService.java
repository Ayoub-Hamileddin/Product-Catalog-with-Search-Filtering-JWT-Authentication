package com.example.product_catalog.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.product_catalog.payload.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProduct(Pageable  pageable , String search ,String name,Double minPrice, Double maxPrice,Boolean inStock,Double rating);

    ProductDTO getProductById(Long Id) throws Exception;

    ProductDTO createProduct(ProductDTO productDTO) throws Exception;

    ProductDTO updateProduct(Long ProductId,ProductDTO productDTO) throws Exception;

    void deleteProduct(Long Id) throws Exception;
}

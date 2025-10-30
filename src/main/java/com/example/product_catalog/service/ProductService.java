package com.example.product_catalog.service;

import java.util.List;

import com.example.product_catalog.payload.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProduct();
    ProductDTO getProductById();
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long ProductId,ProductDTO productDTO);
    void deleteProduct();
}

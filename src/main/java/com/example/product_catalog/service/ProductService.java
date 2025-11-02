package com.example.product_catalog.service;

import java.util.List;

import com.example.product_catalog.payload.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProduct( String name,
                                   String description,
                                   String categoryName,
                                   Double minPrice,
                                   Double maxPrice,
                                   Boolean InStock,
                                   String  orderBy,
                                   String  sortBy);
    ProductDTO getProductById(Long Id) throws Exception;
    ProductDTO createProduct(ProductDTO productDTO) throws Exception;
    ProductDTO updateProduct(Long ProductId,ProductDTO productDTO) throws Exception;
    void deleteProduct(Long Id) throws Exception;
}

package com.example.product_catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_catalog.payload.dto.ProductDTO;
import com.example.product_catalog.payload.response.ApiResponse;
import com.example.product_catalog.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
        private final ProductService productService;

@PostMapping
public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws Exception {
    
    return ResponseEntity.ok(productService.createProduct(productDTO));
}

@PutMapping("/{id}")
public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable("id") Long id ) throws Exception {
    return ResponseEntity.ok(productService.updateProduct(id,productDTO));
}

@DeleteMapping("/{id}")
public ApiResponse deleteProduct(@PathVariable("id") Long id ) throws Exception {
    productService.deleteProduct(id);
    return ApiResponse.builder()
        .message("product deleted successfuly" + id)
    .build();
}


@GetMapping
public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam("name") String name,
                                  @RequestParam("description") String description,
                                  @RequestParam("categoryName") String categoryName,
                                  @RequestParam("minPrice") Double minPrice,
                                  @RequestParam("maxPrice") Double maxPrice,
                                  @RequestParam("InStock") Boolean InStock,
                                  @RequestParam("orderBy") String  orderBy,
                                  @RequestParam("sortBy") String  sortBy ) throws Exception {
    return ResponseEntity.ok(productService.getAllProduct(name, description, categoryName, minPrice, maxPrice, InStock, orderBy, sortBy));
}


@GetMapping("/{id}")
public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id ) throws Exception {
    return ResponseEntity.ok(productService.getProductById(id));
}


}

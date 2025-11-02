package com.example.product_catalog.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product_catalog.entity.Category;
import com.example.product_catalog.entity.Product;
import com.example.product_catalog.mapper.ProductMapper;
import com.example.product_catalog.payload.dto.ProductDTO;
import com.example.product_catalog.repository.CategoryRepository;
import com.example.product_catalog.repository.ProductRepository;
import com.example.product_catalog.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository; 
    private final CategoryRepository categoryRepository; 

    @Override
    public List<ProductDTO> getAllProduct(String name,
                                   String description,
                                   String categoryName,
                                   Double minPrice,
                                   Double maxPrice,
                                   Boolean InStock,
                                   String  orderBy,
                                   String  sortBy) {
       List<Product> products = productRepository.findProducts(name, description, categoryName, minPrice, maxPrice, InStock, orderBy, sortBy);
       return products
            .stream()
            .map(product ->  ProductMapper.ToDTO(product))
            .toList();
        }

    @Override
    public ProductDTO getProductById(Long Id) throws Exception {
        Product product=productRepository.findById(Id).orElseThrow(
            ()->new Exception("product not found")
        );
        return ProductMapper.ToDTO(product);
    }





    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws Exception {
       Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
            ()->new Exception("category not found")
        );

       Product newProduct= ProductMapper.ToEntity(productDTO, category);

       return ProductMapper.ToDTO(productRepository.save(newProduct));



    }

    @Override
    public ProductDTO updateProduct(Long ProductId, ProductDTO productDTO) throws Exception {


          Product product=productRepository.findById(ProductId).orElseThrow(
            ()->new Exception("product not found")
        );

         Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
            ()->new Exception("category not found")
        );

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setRating(productDTO.getRating());
        product.setInStock(productDTO.getInStock());
        product.setCategory(category);

       
        return ProductMapper.ToDTO( productRepository.save(product));


    }

    @Override
    public void deleteProduct(Long Id) throws Exception {
        Product product=productRepository.findById(Id).orElseThrow(
            ()->new Exception("product not found")
        );


        productRepository.delete(product);

    }
}

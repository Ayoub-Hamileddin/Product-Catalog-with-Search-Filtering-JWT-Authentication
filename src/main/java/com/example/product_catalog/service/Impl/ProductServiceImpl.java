package com.example.product_catalog.service.Impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<ProductDTO> getAllProduct(Pageable pageable , String search) {
       List<Product> products = search == null?
                     productRepository.findAll(pageable).getContent()
                    :productRepository.findByName(search, pageable).getContent();
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

         
       if (productDTO.getName()!=null) {product.setName(productDTO.getName());}

       if (productDTO.getDescription()!=null) {product.setDescription(productDTO.getDescription());}

       if (productDTO.getPrice()!=null) {product.setPrice(productDTO.getPrice()); }

       if (productDTO.getRating()!=null) {product.setRating(productDTO.getRating()); }

       if (productDTO.getInStock()!=null) {product.setInStock(productDTO.getInStock());}

       if (productDTO.getCategoryId()!=null) {
             Category category=categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
            ()->new Exception("category not found")
        );
             product.setCategory(category);
        }

       

       
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

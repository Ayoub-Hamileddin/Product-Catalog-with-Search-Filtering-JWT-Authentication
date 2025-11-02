package com.example.product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.product_catalog.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(
        "SELECT p from Product p "+ 
        "WHERE ("+
        " LOWER(p.name) LIKE LOWER(CONCAT('%',:name,'%')) OR"+
        " LOWER(p.description) LIKE LOWER(CONCAT('%',:description,'%'))) AND (  " +
        " (p.category.id = :categoryName) OR "+
        " (p.price >= :minPrice)  OR " +
        " (p.price <= :maxPrice) OR " +
        " (p.inStock = true) ) " +
        " ORDER BY :orderBy ASC "+
        " LIMIT 10 OFFSET 10 "
    )
    List<Product> findProducts(@Param("name") String name,
                                  @Param("description") String description,
                                  @Param("categoryName") String categoryName,
                                  @Param("minPrice") Double minPrice,
                                  @Param("maxPrice") Double maxPrice,
                                  @Param("InStock") Boolean InStock,
                                  @Param("orderBy") String  orderBy,
                                  @Param("sortBy") String  sortBy
                                  );
}

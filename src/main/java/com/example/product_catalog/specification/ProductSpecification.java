package com.example.product_catalog.specification;


import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.Specification;

import com.example.product_catalog.entity.Category;
import com.example.product_catalog.entity.Product;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;


public class ProductSpecification {


         // * ----------------------- Searching--------------------
      public static Specification<Product> hasKeyword(String search){
            return (root, query, criteriaBuilder) ->{
               if(search==null || search.isEmpty()) return criteriaBuilder.conjunction();
                     return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%"+search+"%"),
                        criteriaBuilder.like(root.get("description"), "%"+search+"%")
                     );
      };
         
   }


      // * ----------------------- filter by Category--------------------
      public static Specification<Product> withCategory(String name){
            return (root,query,cb)->{
              if(name==null) return cb.conjunction();
               Join<Product,Category> joinCategory=root.join("category");
               return cb.equal(joinCategory.get("name"), name);
            };
      }



         // * ----------------------- Price range filtering--------------------
      public static Specification<Product> rangePrice(Double minPrice,Double maxPrice){
            return (root,query,cb)->{
               List<Predicate>predicates=new ArrayList<>();

               if (minPrice !=null) {
                     predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
               }

               if (maxPrice !=null) {
                     predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
               }

               return cb.and(predicates.toArray(new Predicate[0]));

            };
      }


      // * ----------------------- filter availability--------------------
      public static Specification<Product> availibilty(Boolean inStock){
         return (root,query,cb)->{
            if(inStock ==null) return cb.conjunction();
            return cb.equal(root.get("inStock"), inStock);

         };
      }




      // * ----------------------- rating --------------------
      public static Specification<Product> rating(Double rating){
         return (root,query,cb)->{
            if(rating ==null) return cb.conjunction();
           Predicate predicate = cb.lessThanOrEqualTo(root.get("rating"), rating);
           query.orderBy(cb.desc(root.get("rating")));

            return predicate;
         };
      }


 
}



package com.example.product_catalog.specification;


import org.springframework.data.jpa.domain.Specification;

import com.example.product_catalog.entity.Category;
import com.example.product_catalog.entity.Product;

import jakarta.persistence.criteria.Join;


public class ProductSpecification {

      public static Specification<Product> hasKeyword(String search){
            return (root, query, criteriaBuilder) ->{
               if(search==null || search.isEmpty()) return criteriaBuilder.conjunction();
                  String like ="%" +search.toLowerCase()+ "%";
                     return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), search),
                        criteriaBuilder.like(root.get("description"), search)
                     );
      };
         
      }

      public static Specification<Product> withCategory(String name){
            return (root,query,cb)->{
              if(name==null) return cb.conjunction();
               Join<Product,Category> joinCategory=root.join("category");
               return cb.equal(joinCategory.get("name"), name);
            };
      }

      // public static Specification<Product> rangePrice(Long id){
      //       return (root,query,cb)->{
      //         if(id==null) return cb.conjunction();
      //          Join<Product,Category> joinCategory=root.join("category");
      //          return cb.equal(joinCategory.get("id"), id);
      //       };
      // }


 
}


// return new Specification<Product>() {

//         @Override
//         public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (search==null || search.isEmpty()) {
//                 criteriaBuilder.conjunction();
//            }

//            List<Predicate> list=new ArrayList<>();
//            list.add(criteriaBuilder.equal(root.get("name"), search));
//            list.add(criteriaBuilder.equal(root.get("description"), search));
//            return null;
//         }
        
//     };
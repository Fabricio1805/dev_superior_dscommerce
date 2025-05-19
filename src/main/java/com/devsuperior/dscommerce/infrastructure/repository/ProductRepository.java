package com.devsuperior.dscommerce.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscommerce.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(value = "SELECT product FROM Product product JOIN FETCH product.categories", 
        countQuery = "SELECT COUNT(product) FROM Product product JOIN product.categories"
    )
    Page<Product> searchAll(Pageable pageable);
}

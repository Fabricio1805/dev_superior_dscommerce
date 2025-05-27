package com.devsuperior.dscommerce.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscommerce.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}

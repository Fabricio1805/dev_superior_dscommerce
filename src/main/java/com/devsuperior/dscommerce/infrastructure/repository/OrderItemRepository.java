package com.devsuperior.dscommerce.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscommerce.domain.entity.OrderItem;
import com.devsuperior.dscommerce.domain.entity.OrderItemPK;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
    
}

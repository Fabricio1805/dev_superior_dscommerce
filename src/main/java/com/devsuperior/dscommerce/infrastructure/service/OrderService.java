package com.devsuperior.dscommerce.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.application.dto.OrderDTO;
import com.devsuperior.dscommerce.domain.entity.Order;
import com.devsuperior.dscommerce.infrastructure.exception.NotFoundException;
import com.devsuperior.dscommerce.infrastructure.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO findById(final Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        return new OrderDTO(order);
    }
}

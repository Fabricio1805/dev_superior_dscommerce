package com.devsuperior.dscommerce.infrastructure.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.application.dto.OrderDTO;
import com.devsuperior.dscommerce.application.dto.OrderItemDTO;
import com.devsuperior.dscommerce.domain.entity.Order;
import com.devsuperior.dscommerce.domain.entity.OrderItem;
import com.devsuperior.dscommerce.domain.entity.Product;
import com.devsuperior.dscommerce.domain.enums.OrderStatus;
import com.devsuperior.dscommerce.infrastructure.exception.NotFoundException;
import com.devsuperior.dscommerce.infrastructure.repository.OrderItemRepository;
import com.devsuperior.dscommerce.infrastructure.repository.OrderRepository;
import com.devsuperior.dscommerce.infrastructure.repository.ProductRepository;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    public OrderDTO findById(final Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        authService.validSelfOrAdmin(order.getClient().getId());

        return new OrderDTO(order);
    }

    public OrderDTO save(final OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());
        for (OrderItemDTO orderItemDTO : orderDTO.getItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());

            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setOrder(order);
            orderItem.setProduct(product);

            order.getItems().add(orderItem);
        }

        Order orderSaved = orderRepository.save(order);
        orderItemRepository.saveAll(orderSaved.getItems());

        return new OrderDTO(orderSaved);
    }
}

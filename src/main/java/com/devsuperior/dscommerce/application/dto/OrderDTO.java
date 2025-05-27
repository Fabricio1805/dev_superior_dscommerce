package com.devsuperior.dscommerce.application.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.devsuperior.dscommerce.domain.entity.Order;
import com.devsuperior.dscommerce.domain.enums.OrderStatus;

public class OrderDTO {
    private Long id;

    private Instant moment;

    private OrderStatus status;

    private ClientDTO client;

    private PaymentDTO payment;

    private Set<OrderItemDTO> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public Set<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemDTO> items) {
        this.items = items;
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment,
            Set<OrderItemDTO> items) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
        this.items = items;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.client = new ClientDTO(order.getClient());
        this.payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());

        order.getItems().forEach(i -> {
            items.add(new OrderItemDTO(i));
        });
    }


    public Double getTotal () {
        Double total = 0.0;
        for (OrderItemDTO orderItemDTO : items) {
            total += orderItemDTO.getSubTotal(); 
        }
        return total;
    }
    
}

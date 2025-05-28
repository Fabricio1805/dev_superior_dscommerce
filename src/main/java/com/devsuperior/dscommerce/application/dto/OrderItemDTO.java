package com.devsuperior.dscommerce.application.dto;

import com.devsuperior.dscommerce.domain.entity.OrderItem;

public class OrderItemDTO {
    private Long productId;
    private String name;
    private Integer quantity;
    private Double price;
    private String imageUrl;
    
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    
    public OrderItemDTO(Long productId, String name, Integer quantity, Double price, String imageUrl) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.name = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.imageUrl = orderItem.getProduct().getImageUrl();
    }
    

    public Double getSubTotal(){
        return price * quantity;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.devsuperior.dscommerce.application.dto;

import com.devsuperior.dscommerce.domain.entity.Product;
import com.devsuperior.dscommerce.domain.entity.User;

public class ProductMinDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private double price;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public ProductMinDTO() {
    }    


    public ProductMinDTO(Product product) {
        id = product.getId();
        name = product.getName();
        imageUrl = product.getImageUrl();
        price = product.getPrice();
    }       
}

package com.devsuperior.dscommerce.application.dto;

import com.devsuperior.dscommerce.domain.entity.ProductCategory;

public class ProductCategoryDTO {
    private Long id;

    private String name;

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

    public ProductCategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategoryDTO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
    }
}

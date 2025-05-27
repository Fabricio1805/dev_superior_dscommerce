package com.devsuperior.dscommerce.application.dto;

import com.devsuperior.dscommerce.domain.entity.ProductCategory;

public class CategoryDTO {
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

    public CategoryDTO() {
    }


    public CategoryDTO(ProductCategory productCategory) {
        id = productCategory.getId();
        name = productCategory.getName();
    }
}

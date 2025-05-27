package com.devsuperior.dscommerce.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dscommerce.domain.entity.Product;
import com.devsuperior.dscommerce.domain.entity.ProductCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    private Long id;

    @Size(min = 3, max = 80, message = "nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "nome é requerido")
    private String name;
    
    @Size(min = 10, message = "Descrição precisa ter no minimo 10 caracteres")
    @NotBlank(message = "Descrição é requerido")
    private String description;

    private String imageUrl;

    @Positive(message = "o Preço precisa ser positivo")
    private double price;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {
    }

    
    public ProductDTO(Long id, String name, String description, String imageUrl, double price, List<CategoryDTO> categoryDTOs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        imageUrl = entity.getImageUrl();
        price = entity.getPrice();

        for (ProductCategory productCategory : entity.getCategories()) {
            categories.add(new CategoryDTO(productCategory));
        }
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public List<CategoryDTO> getCategories() {
        return categories;
    }
}

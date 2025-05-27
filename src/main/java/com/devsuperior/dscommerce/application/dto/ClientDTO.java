package com.devsuperior.dscommerce.application.dto;

import com.devsuperior.dscommerce.domain.entity.User;

public class ClientDTO {
    
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
    
    public ClientDTO() {
    }
    
    public ClientDTO(User client) {
        id = client.getId();
        name = client.getName();
    }
}

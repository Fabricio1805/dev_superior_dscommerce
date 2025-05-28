package com.devsuperior.dscommerce.infrastructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.application.dto.ProductCategoryDTO;
import com.devsuperior.dscommerce.domain.entity.ProductCategory;
import com.devsuperior.dscommerce.infrastructure.repository.ProductCategoryRepository;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategoryDTO> findAll() {
        List<ProductCategory> categories = productCategoryRepository.findAll();

        return categories.stream().map(c -> new ProductCategoryDTO(c)).toList();
    }
}

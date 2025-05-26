package com.devsuperior.dscommerce.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.devsuperior.dscommerce.application.dto.ProductDTO;
import com.devsuperior.dscommerce.application.dto.ProductMinDTO;
import com.devsuperior.dscommerce.domain.entity.Product;
import com.devsuperior.dscommerce.infrastructure.exception.DatabaseException;
import com.devsuperior.dscommerce.infrastructure.exception.NotFoundException;
import com.devsuperior.dscommerce.infrastructure.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO findById(final Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        return new ProductDTO(product);
    }

    public Page<ProductMinDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.searchAll(pageable);

        return products.map(p -> new ProductMinDTO(p));
    }

    public ProductDTO save(ProductDTO productDTO) {

        Product product = new Product();
        toDtoFromEntity(productDTO, product);

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    public void update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        toDtoFromEntity(productDTO, product);

        productRepository.save(product);

    }


    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.SUPPORTS )
    public void delete(Long id) {
        if(!productRepository.existsById(id)){
            throw new NotFoundException("Produto não encontrado");
        }

        try {
            productRepository.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Falha na integridade referencial");
        }
        
    }

    private void toDtoFromEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
    }
}

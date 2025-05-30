package com.devsuperior.dscommerce.application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.application.dto.ProductDTO;
import com.devsuperior.dscommerce.application.dto.ProductMinDTO;
import com.devsuperior.dscommerce.infrastructure.service.ProductService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable final Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(productService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO result = productService.save(productDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@Valid @RequestBody ProductDTO entity) {
        productService.update(id, entity);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

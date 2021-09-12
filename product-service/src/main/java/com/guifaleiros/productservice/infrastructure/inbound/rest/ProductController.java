package com.guifaleiros.productservice.infrastructure.inbound.rest;

import com.guifaleiros.productservice.application.domain.Product;
import com.guifaleiros.productservice.application.services.ProductService;
import com.guifaleiros.productservice.infrastructure.inbound.rest.dto.CreateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductDTO productDTO) {
        var product = this.productService.save(productDTO.toDomainModel());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}

package com.piphub.springjwtauthentication.controllers.rest;

import com.piphub.springjwtauthentication.models.Product;
import com.piphub.springjwtauthentication.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllCategories() {
        log.info("Intercept get all products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Product req) {
        log.info("Intercept create product {}", req);
        productService.create(req);
        return new ResponseEntity<>("Create Product Success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id) {
        log.info("Intercept get product by id {}", id);
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }
}

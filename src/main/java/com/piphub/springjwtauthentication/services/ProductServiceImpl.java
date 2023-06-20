package com.piphub.springjwtauthentication.services;

import com.piphub.springjwtauthentication.models.Product;
import com.piphub.springjwtauthentication.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}

package com.piphub.springjwtauthentication.services;

import com.piphub.springjwtauthentication.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void create(Product product);

    Product getById(Integer id);

}

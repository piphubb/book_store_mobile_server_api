package com.piphub.springjwtauthentication.services;

import com.piphub.springjwtauthentication.models.Category;
import com.piphub.springjwtauthentication.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> findAllProductByActive();

    void create(Product product);

    Product getById(Integer id);

    void delete(Product req);
}

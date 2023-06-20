package com.piphub.springjwtauthentication.repository;

import com.piphub.springjwtauthentication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}


package com.piphub.springjwtauthentication.repository;

import com.piphub.springjwtauthentication.models.Category;
import com.piphub.springjwtauthentication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStatus(String status);
    List<Product> findAllByStatusIn(List<String> statusList);
}


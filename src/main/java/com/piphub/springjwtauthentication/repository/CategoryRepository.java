package com.piphub.springjwtauthentication.repository;

import com.piphub.springjwtauthentication.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatus(String status);

    List<Category> findAllByStatusIn(List<String> statusList);
}
